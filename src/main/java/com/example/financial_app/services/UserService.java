package com.example.financial_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.financial_app.DTOs.Notification.NotificationDTO;
import com.example.financial_app.DTOs.User.CreateUserDTO;
import com.example.financial_app.DTOs.User.UserDTO;
import com.example.financial_app.exceptions.CustomException;
import com.example.financial_app.models.Notification;
import com.example.financial_app.models.User;
import com.example.financial_app.repositories.iUserRepository;
import com.example.financial_app.utils.EntityGraphUtil;
import com.example.financial_app.utils.ObjectMapper;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private iUserRepository userRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotificationService notificationService;

    public void createUser(CreateUserDTO user) throws CustomException{
        Boolean userExists = this.userRepository.findByMail(user.getMail()) != null;
        if(userExists) throw new CustomException("User already registered");

        User newUser = new User(user.getName(), 
                                user.getSurname(), 
                                user.getMail(),
                                user.getPassword());
        
        User createdUser = this.userRepository.save(newUser);

        List<Long> notificationIds = user.getNotificationIds();
        if(user.getNotificationIds().size() > 0) this.associateNotifications(createdUser.getId(), notificationIds);
    }

    private void associateNotifications(Long userId, List<Long> notificationIds) throws CustomException{
        Optional<User> userOptional = this.userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new CustomException("User not found"));

        for (Long notificationId : notificationIds) {        

            Notification notification = notificationService.getNotificationById(notificationId);
            if (notification != null) {
                user.getNotifications().add(notification);
            }
        }

        userRepository.save(user);
    }

    public UserDTO getUserById(Long userId) throws CustomException{
        EntityGraph<User> userGraph = EntityGraphUtil.createEntityGraph(this.entityManager, User.class, "notifications", "subscriptions");
        User user = this.entityManager.createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class)
                            .setParameter("userId", userId)
                            .setHint("jakarta.persistence.fetchgraph", userGraph)
                            .getSingleResult();
        if(user == null) throw new CustomException("User not found");
        UserDTO userDTO = objectMapper.mapEntity(user, UserDTO.class);

        return userDTO;
    }

    public List<UserDTO> getUsers(){
        EntityGraph<User> userGraph = EntityGraphUtil.createEntityGraph(this.entityManager, User.class, "notifications", "subscriptions");
        
        List<User> users = this.entityManager.createQuery("SELECT u FROM User u", User.class)
                                        .setHint("jakarta.persistence.fetchgraph", userGraph)
                                        .getResultList();
        List<UserDTO> result = objectMapper.mapEntities(users, UserDTO.class);
        
        return result;
    }

    public void deleteUser(Long userId) throws CustomException{        
        Optional<User> userOptional = this.userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new CustomException("User not found"));
        this.userRepository.delete(user);
    }

}
