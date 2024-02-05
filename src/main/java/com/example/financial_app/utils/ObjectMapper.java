package com.example.financial_app.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    private <T, D> D mapObject(T object, Class<D> dtoClass, String... excludeFields) {
        modelMapper.getConfiguration().setPropertyCondition(ctx -> {
            // Exclude specified fields from mapping
            String propertyName = ctx.getMapping().getLastDestinationProperty().getName();
            for (String excludeField : excludeFields) {
                if (propertyName.equals(excludeField)) {
                    return false;
                }
            }
            return true;
        });
        return modelMapper.map(object, dtoClass);
    }

    public <T, D> List<D> mapEntities(List<T> entities, Class<D> dtoClass, String... excludeFields) {
        return entities.stream()
                       .map(entity -> this.mapObject(entity, dtoClass, excludeFields))
                       .collect(Collectors.toList());
    }

    public <T, D> D mapEntity(T entity, Class<D> dtoClass, String... excludeFields) {
        return this.mapObject(entity, dtoClass, excludeFields);
    }
}