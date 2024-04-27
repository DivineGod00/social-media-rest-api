package com.application.social.media.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.social.media.model.master.User;
import com.application.social.media.model.master.UserCredentials;

@Repository
public interface SignUpRepo  extends JpaRepository<User , Long>{

	
	
	User findByContactNoAndEmail(String contactNo, String email);
	
	User findByContactNo(String contactNo);
	
	User findByEmail(String mail);
	
	User findByName(String name);
}
