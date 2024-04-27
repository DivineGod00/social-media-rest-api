package com.application.social.media.interfaces.implementation;


import static com.application.social.media.common.methods.CommonUtilities.failure;
import static com.application.social.media.common.methods.CommonUtilities.success;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.signUp.SignUpDto;
import com.application.social.media.dto.user.UserDto;
import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.model.master.User;
import com.application.social.media.service.SignInandUp.SignInService;
import com.application.social.media.service.SignInandUp.SignUpService;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class SocialMediaEndUserServiceImple implements SocialMediaApiProcessor{

	private static final  Logger logger = LoggerFactory.getLogger(SocialMediaEndUserServiceImple.class);
	

	
	@Autowired
	private SignInService signInService;
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public ClientResponse processSignUp(ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("--- add user api initiated ---");
		ClientResponse response = null;
		logger.info("request : "+CommonUtilities.convertObjectToJsonString(request));
		SignUpDto user = CommonUtilities.extractObject(request.getData(), SignUpDto.class);
		try {
			response = signUpService.processSignUp(user, httpServletRequest);
			logger.info("Data ===> "+response);
		}
		catch(Exception e)
		{
			response = failure(StatusCode.SOME_ERROR_OCCURED, e.getMessage(),
					Constants.SOME_ERROR_OCCURED);
		}
		return response;
		
	}
	
	
	
	
	@Override
	public ClientResponse processSignIn(ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("--- login api initiated ---");
		ClientResponse response = null;
		response = signInService.processSignIn(request, httpServletRequest);
		
		
		
		
		return response;
	}
	
	
	
	
	
	
	
	@Override
	public ClientResponse processUser(Long id,HttpServletRequest httpServletRequest) throws Exception
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
}
