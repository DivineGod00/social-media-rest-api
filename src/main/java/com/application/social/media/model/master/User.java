package com.application.social.media.model.master;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")

public class User extends BaseClassSQLMaster{

	
	
	
	@Column(name ="name")
	private String name;
	
	@Column(name = "dob")
	private LocalDate birthDate;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contact_no")
	private String contactNo;
}
