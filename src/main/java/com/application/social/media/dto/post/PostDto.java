package com.application.social.media.dto.post;

import com.application.social.media.dto.signUp.SignUpDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {

	private String description;
	private Long likes;
	private Long comments;
	private Long share;
	private String name;
	private String username;
	
}
