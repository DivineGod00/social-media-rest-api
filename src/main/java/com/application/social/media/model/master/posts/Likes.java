package com.application.social.media.model.master.posts;

import com.application.social.media.model.master.BaseClassSQLMaster;
import com.application.social.media.model.master.users.UserCredentials;
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
import lombok.ToString;




@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "likes_count")
public class Likes extends BaseClassSQLMaster{

	@Column(name = "post_id")
	private Long postId;
	
	
	@Column(name = "user_id")
	private Long userId;
}
