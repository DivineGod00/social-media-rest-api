package com.application.social.media.interfaces.implementation;

import static com.application.social.media.common.methods.CommonUtilities.failure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.user.UserDto;
import com.application.social.media.interfaces.InternalApiProcessor;
import com.application.social.media.service.internal.service.UserService;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class InternalApiServiceImple implements InternalApiProcessor {

	private static final  Logger logger = LoggerFactory.getLogger(SocialMediaEndUserServiceImple.class);

	@Autowired
	private UserService userService;
	
	
	@Override
	public ClientResponse processAllUser(HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("--- user Api Initiated ---");
		ClientResponse response = null;
		try {
			response = userService.allUser(httpServletRequest);
			logger.info("successfully fetched Data");
		}
		catch(Exception e)
		{
			response = failure(StatusCode.SOME_ERROR_OCCURED, e.getMessage(),
					Constants.SOME_ERROR_OCCURED);
		}
		return response;
		
	}
	
	@Override
	public ClientResponse processSingleUser(Long id, HttpServletRequest httpServletRequest)throws Exception
	{
			logger.info("--- find one user api initiated ---");
			ClientResponse response = null;
			try
			{
				response = userService.findUser(id, httpServletRequest);
				logger.info("Data ===> "+response);
			}
			catch(Exception e)
			{
				response = failure(StatusCode.SOME_ERROR_OCCURED, e.getMessage(),
						Constants.SOME_ERROR_OCCURED);
			}
			return response;
	}
	

}
