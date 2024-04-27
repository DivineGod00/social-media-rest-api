package com.application.social.media.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.UserCredentials;

@Repository
public interface SignInRepo extends JpaRepository< UserCredentials, Long>{

	UserCredentials findByUsernameAndPassword(String username, String password);
	
	UserCredentials findByUsername(String username);
}