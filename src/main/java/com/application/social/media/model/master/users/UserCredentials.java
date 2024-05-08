package com.application.social.media.model.master.users;

import org.hibernate.annotations.DynamicInsert;

import com.application.social.media.model.master.BaseClassSQLMaster;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@DynamicInsert
@Table(name = "user_credentials")
public class UserCredentials extends BaseClassSQLMaster{

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User userId;
}
