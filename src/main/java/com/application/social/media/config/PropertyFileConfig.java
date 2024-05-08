package com.application.social.media.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource(value = "classpath:application.properties"),
	@PropertySource(value = "classpath:mail-config.properties"),
	@PropertySource(value = "classpath:postgres-sql-config.properties")

    
})
public class PropertyFileConfig {

}
