package com.casa.post_service.controller;

import com.casa.post_service.entity.Post;
import com.casa.post_service.repository.PostRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@RequestMapping("/api")
@RestController
@Log4j2
public class PostController {
    private final PostRepository postRepository;
    private final RestTemplate restTemplate;

    /**
     * This is the constructor for the PostController class.
     * It initializes the PostRepository and RestTemplate objects.
     *
     * @param postRepository Bean of PostRepository for persisting  posts.
     * @param restTemplate Bean of RestTemplate for making HTTP requests.
     */
    PostController(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * This method is used to create a new post.
     * It uses the @PostMapping annotation to map the URL to the method and @RequestBody to bind the request body to the
     * Post object.
     * It returns a ResponseEntity with the saved post and the response from the HTTP request.
     *
     * @param post The post to be created.
     * @return A ResponseEntity with the saved post and the response from the HTTP request.
     */
    @PostMapping("/createNewPost")
    public ResponseEntity<?> createNewPost(@RequestBody Post post) {
        log.info("Hit received on /createNewPost controller: {}", post);
        try {
            Post savedPost = postRepository.save(post);
            String url = "http://worldtimeapi.org/api/timezone/Asia/Kolkata";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("db_post", savedPost);
            responseBody.put("http_outbound", response.getBody());
            return ResponseEntity.ok(responseBody);
        } catch (Exception e) {
            log.error("Error while creating new post: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
