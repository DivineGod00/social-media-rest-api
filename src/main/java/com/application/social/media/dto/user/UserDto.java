package com.application.social.media.dto.user;

import java.time.LocalDate;

import com.application.social.media.model.master.users.User;
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
public class UserDto {

	private String name;
	private String birthDate;
	private String email;
	private String contactNo;
	private String username;
	private String password;
}
