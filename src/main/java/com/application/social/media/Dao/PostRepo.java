package com.application.social.media.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.posts.Posts;


@Repository
public interface PostRepo  extends JpaRepository<Posts , Long>{

	
}
