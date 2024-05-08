package com.application.social.media.model.master.posts;

import com.application.social.media.model.master.BaseClassSQLMaster;
import com.application.social.media.model.master.users.UserCredentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "shares_count")
public class Shares extends BaseClassSQLMaster{


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Posts postId;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private UserCredentials userId;
	
}
