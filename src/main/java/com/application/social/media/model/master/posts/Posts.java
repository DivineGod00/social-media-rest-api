package com.application.social.media.model.master.posts;

import org.hibernate.annotations.ColumnDefault;

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
@Table(name = "post_feed")
public class Posts  extends BaseClassSQLMaster{

	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private UserCredentials userId;
	
	@ColumnDefault("0")
	@Column(name = "likes")
	private Long likes;
	
	@ColumnDefault("0")
	@Column(name = "comments")
	private Long comments;
	
	@ColumnDefault("0")
	@Column(name = "shares")
	private Long shares;
	
	
}
