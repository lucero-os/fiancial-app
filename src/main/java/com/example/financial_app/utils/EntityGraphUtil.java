package com.example.financial_app.utils;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Subgraph;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityGraphUtil {

    //* Creates Entity Graph for given attribute nodes, creates subgraph levels tanking "." as separator in attributeNode  */
    public static <T> EntityGraph<T> createEntityGraph(EntityManager entityManager, Class<T> entityClass, String... attributeNodes) {
        EntityGraph<T> entityGraph = entityManager.createEntityGraph(entityClass);

        Map<String, Subgraph<?>> subgraphMap = new HashMap<>();

        for (String attributeNode : attributeNodes) {
            addAttributeNode(entityManager, entityGraph, subgraphMap, entityClass, attributeNode);
        }

        return entityGraph;
    }

    private static <T> void addAttributeNode(EntityManager entityManager, EntityGraph<T> entityGraph, Map<String, Subgraph<?>> subgraphMap, Class<T> entityClass, String attributeNode) {
        String[] parts = attributeNode.split("\\.");

        Subgraph<?> subgraph = null;

        for (int i = 0; i < parts.length; i++) {
            String attributeName = parts[i];

            if (i == 0) {
                if (subgraphMap.containsKey(attributeName)) {
                    subgraph = subgraphMap.get(attributeName);
                } else {
                    subgraph = entityGraph.addSubgraph(attributeName);
                    subgraphMap.put(attributeName, subgraph);
                }
            } else {
                subgraph = subgraph.addSubgraph(attributeName);
            }

            // Check if the last part of the attributeNode and add the type to the subgraph
            if (i == parts.length - 1) {
                try {
                    Class<?> attributeType = entityManager.getMetamodel().managedType(entityClass).getAttribute(attributeName).getJavaType();
                    Field[] fields = attributeType.getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true);
                        subgraph.addAttributeNodes(field.getName());
                    }
                } catch (IllegalArgumentException e) {
                    // Handle exception as needed
                    e.printStackTrace();
                }
            }
        }
    }
}

