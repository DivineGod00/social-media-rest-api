package com.application.social.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(servers = {@Server(url = "/")}
				   ,info = @Info(title = "Social-Media-Api", version = "1.0", description = "Used for development only.Not allowed for public use or any third party."
				   ,contact = @Contact(
				      name = "Devanshu",
				      email = "SocailMedia.01@gmail.com")))
public class SocialMediaRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaRestApiApplication.class, args);
	}
//	http://localhost:8080/swagger-ui/index.html
}