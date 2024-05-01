package com.application.social.media.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:mail-config.properties"),
    @PropertySource("classpath:postgres-sql-config.properties")
    
})
public class PropertyFileConfig {

}
