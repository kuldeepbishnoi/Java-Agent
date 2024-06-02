package com.casa.agent_service.entity;


/**
 * This class represents a Post entity in the application.
 * It uses the @Entity annotation to indicate that it is a JPA entity.
 * The @Data annotation from Lombok generates getters, setters, equals, hashCode and toString methods.
 * that will be used by objectMapper to convert the JSON request body to a Post object.
 */
public class Post {
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostContents() {
        return postContents;
    }

    public void setPostContents(String postContents) {
        this.postContents = postContents;
    }

    /**
     * This field represents the ID of the post.
     * It uses the @Id annotation to indicate that it is the primary key of the entity.
     * The @GeneratedValue annotation is used to specify how the primary key should be generated.
     */
    private Long Id;
    private String postName;
    private String postContents;
}
