package com.application.social.media.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.Posts;


@Repository
public interface PostRepo  extends JpaRepository<Posts , Long>{

}
