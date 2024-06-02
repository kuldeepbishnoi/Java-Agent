package com.casa.post_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * This class represents a Post entity in the application.
 * It uses the @Entity annotation to indicate that it is a JPA entity.
 * The @Data annotation from Lombok generates getters, setters, equals, hashCode and toString methods.
 * that will be used by objectMapper to convert the JSON request body to a Post object.
 */
@Data
@Entity
public class Post {
    /**
     * This field represents the ID of the post.
     * It uses the @Id annotation to indicate that it is the primary key of the entity.
     * The @GeneratedValue annotation is used to specify how the primary key should be generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String postName;
    private String postContents;
}
