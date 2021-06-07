package com.isep.project.config;

import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @author : Xuan MIAO
 * @version : 1.0.0
 * @date : 2021/5/23
 */
@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer
{

    private static final long MAX_AGE_SECS = 3600;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
            CorsRegistry cors)
    {

        cors.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(false)
                .maxAge(MAX_AGE_SECS);
    }
}