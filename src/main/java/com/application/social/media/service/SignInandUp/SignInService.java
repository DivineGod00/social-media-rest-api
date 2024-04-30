package com.application.social.media.service.SignInandUp;

import static com.application.social.media.common.methods.CommonUtilities.failure;
import static com.application.social.media.common.methods.CommonUtilities.success;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.signIn.SignInDto;
import com.application.social.media.internal.Dao.UserCredentialsRepo;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.model.master.User;
import com.application.social.media.model.master.UserCredentials;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class SignInService {

	private static final Logger logger = LoggerFactory.getLogger(SignInService.class);
	
	@Autowired
	private UserCredentialsRepo userCredentialsRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommonUtilities commonUtilities;
	
	
	
	public ClientResponse processSignIn(ClientRequest request, HttpServletRequest httpServletRequest)throws Exception
	{

		logger.info("request : "+CommonUtilities.convertObjectToJsonString(request));
		SignInDto userCreden = commonUtilities.extractObject(request.getData(), SignInDto.class);
		Map<String, String> data = new HashMap<>();
		boolean isValid =commonUtilities.checkingCredentials(userCreden.getUsername(), userCreden.getPassword());
		User user = null;
		if(isValid)
		{
			UserCredentials lm = userCredentialsRepo.findByUsernameAndPassword(userCreden.getUsername(), userCreden.getPassword());
			user = userRepo.findById(lm.getUserId().getId()).orElse(null);
			URI location = ServletUriComponentsBuilder.fromPath("localhost:8080/social-media/user")
					.query("id={id}")
					.buildAndExpand(user.getId())
					.toUri();  
			logger.info("path "+location.getClass());
			data.put("url", location.toString());
			return success(data,"Successfully fetched..");	
		}
		
		else
		{
			data.put("error", "Credentials Not matched with any one SignUp Please !!!");
			return failure(StatusCode.NOT_FOUND, data,
					Constants.USER_NOT_FOUND);
		}
	}
}
