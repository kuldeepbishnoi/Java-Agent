package com.casa.post_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * This class is a configuration class for the RestTemplate bean.
 * It uses Spring's @Configuration annotation to indicate that it is a source of bean definitions.
 * The @Bean annotation is used on the restTemplate method to indicate that it should be used to create a bean in the
 * Spring application context.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * This method creates a new instance of the RestTemplate class and returns it.
     * The RestTemplate class is a synchronous HTTP client that offers a convenient way to consume web services.
     * The @Bean annotation indicates that this method produces a bean to be managed by the Spring container.
     *
     * @return A new instance of the RestTemplate class.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
