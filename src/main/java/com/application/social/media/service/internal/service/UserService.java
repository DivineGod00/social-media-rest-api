package com.application.social.media.service.internal.service;



import static com.application.social.media.common.methods.CommonUtilities.failure;
import static com.application.social.media.common.methods.CommonUtilities.success;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.social.media.dto.user.UserDto;
import com.application.social.media.internal.Dao.UserCredentialsRepo;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.model.master.User;
import com.application.social.media.model.master.UserCredentials;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;




@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserCredentialsRepo ucRepo;
	
	public ClientResponse allUser(HttpServletRequest httpServletRequest)throws Exception
	{
		ClientResponse response = new ClientResponse();
		List<User> users = userRepo.findAll();
		
		response.setData(users);
		
		
		return success(response.getData(),"Fetched Successfully ...");
	}
	
	public ClientResponse findUser(Long id, HttpServletRequest httpServletRequest)throws Exception
	{
		ClientResponse response = new ClientResponse();
		User user = userRepo.findById(id).orElse(null);	
		if(user == null)
		{			 
			response = failure(StatusCode.NOT_FOUND, user, Constants.USER_IS_NOT_FOUND);
		}
		else
		{
			response = success(user,"Successfully Fetched ..");
		}
		
		return response;
	}
	
	public ClientResponse processToAddUser(UserDto user, HttpServletRequest httpServletRequest)throws Exception
	{
		logger.info(" Details of User : "+user);
		
		if(user.getName().length() < 2)
		{			 
			return failure(StatusCode.VALIDATION_ERROR, user, "Name char is more than 2.");
		}
		LocalDate bd = LocalDate.parse(user.getBirthDate());
		logger.info("Time "+bd);
		if (bd.isEqual(LocalDate.now())) {
		    return failure(StatusCode.VALIDATION_ERROR, user, "Birth Date should not be today's date.");
		}
		User userRequest = addUser(user);
		UserCredentials ucSave = addCredentails(user);
		return success(null,"User Saved Successfully");
	}
	
	
	private User addUser(UserDto user)
	{
		User addUser = new User();
		addUser.setName(user.getName());
		addUser.setBirthDate(LocalDate.parse(user.getBirthDate()));
		addUser.setEmail(user.getEmail());
		addUser.setContactNo(user.getContactNo());
		addUser.setIsActive(true);
		userRepo.save(addUser);
		return addUser;
	}
	
	private UserCredentials addCredentails(UserDto user)
	{
		UserCredentials uc = new UserCredentials();
		uc.setUsername(user.getUsername());
		uc.setPassword(user.getPassword());
		User us = userRepo.findByName(user.getName());
		uc.setUserId(us);
		
		ucRepo.save(uc);
		
		return uc;
	}
	
	
}
