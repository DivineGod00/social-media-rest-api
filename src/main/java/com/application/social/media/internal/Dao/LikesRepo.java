package com.application.social.media.internal.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.posts.Likes;

@Repository
public interface LikesRepo extends JpaRepository<Likes, Long>{
	
	Likes findByPostIdAndUserId(long postId, long userId);
	
//	@Query("SELECT COUNT(l) FROM Like l WHERE l.post.id = :postId AND l.user.id = :userId")
//    int countLikesByPostIdAndUserId(Long postId, Long userId);
	
}