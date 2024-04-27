package com.application.social.media.internal.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.UserCredentials;



@Repository
public interface UserCredentialsRepo extends JpaRepository< UserCredentials, Long>{

	
	UserCredentials findByUsernameAndPassword(String username, String password);
}
