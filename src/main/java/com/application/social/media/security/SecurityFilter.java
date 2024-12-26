package com.application.social.media.security;

//import static org.springframework.security.config.Customizer.withDefaults;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;




//@Configuration
//@EnableWebSecurity
public class SecurityFilter {

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//		http.authorizeHttpRequests(
//						auth -> {
//							auth.anyRequest().authenticated();
//						});
//		
//		http.sessionManagement(
//						session -> 
//							session.sessionCreationPolicy(
//									SessionCreationPolicy.STATELESS)
//						);
//		
//		//http.formLogin();
//		http.httpBasic(withDefaults());
//		
//		http.csrf(csrf -> csrf.disable());
//		
//		return http.build();
//	}
//	@Bean
//	public UserDetailsService userDetailService() {
//		
//		var user = User.withUsername("in28minutes")
//			.password("{noop}dummy")
//			.roles("USER")
//			.build();
//
//		
//		var admin = User.withUsername("admin")
//				.password("{noop}dummy")
//				.roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	
}
