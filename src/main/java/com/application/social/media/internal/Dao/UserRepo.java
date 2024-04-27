package com.application.social.media.internal.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.User;

@Repository
public interface UserRepo extends JpaRepository<User , Long>{

	User findByName(String name);
}
