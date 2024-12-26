package com.application.social.media.filter;

import java.io.IOException;

import org.apache.logging.log4j.core.config.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletException;



@Component
@Order(1)
public class CorsFilter implements WebMvcConfigurer{

	
	private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Inside Cors Filter");

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600); // Max age of the CORS preflight request
    }
}
