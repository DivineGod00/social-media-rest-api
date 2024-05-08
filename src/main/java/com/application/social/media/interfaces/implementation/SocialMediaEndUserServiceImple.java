package com.application.social.media.interfaces.implementation;


import static com.application.social.media.common.methods.CommonUtilities.failure;
import static com.application.social.media.common.methods.CommonUtilities.success;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.social.media.Dao.PostRepo;
import com.application.social.media.Dao.SignInRepo;
import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.post.PostDto;
import com.application.social.media.dto.signUp.SignUpDto;
import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.model.master.posts.Posts;
import com.application.social.media.model.master.users.User;
import com.application.social.media.model.master.users.UserCredentials;
import com.application.social.media.service.Post.LikePostService;
import com.application.social.media.service.Post.PostService;
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
	private LikePostService likePostService;
	
	@Autowired
	private CommonUtilities commonUtils;
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PostService postService;

	@Override
	public ClientResponse processSignUp(ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("--- Sign-Up api initiated ---");
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
		logger.info("--- Sign In api initiated ---");
		ClientResponse response = null;
		try {
			response = signInService.processSignIn(request, httpServletRequest);
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
	public ClientResponse processUser(Long id,HttpServletRequest httpServletRequest) throws Exception
	{
		ClientResponse response = new ClientResponse();
		User user = userRepo.findById(id).orElse(null);	
		if(user == null)
		{			 
			response = failure(StatusCode.NOT_FOUND, user, Constants.USER_NOT_FOUND);
		}
		else
		{
			response = success(user,"Successfully Fetched ..");
		}
		
		return response;
	}
	
	
	@Override
	public ClientResponse processPost(ClientRequest request,Long id, HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("--- Post api initiated ---");
		ClientResponse response = null;	
		logger.info("request : "+CommonUtilities.convertObjectToJsonString(request));
		PostDto post = CommonUtilities.extractObject(request.getData(),PostDto.class);
		try {
			response = postService.processPost(post, id, httpServletRequest);
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
	public ClientResponse processSinglePost(Long postId, Long userId, HttpServletRequest httpServletRequest) throws Exception
	{
		ClientResponse response = null;
		try {
			response = postService.processSinglePost(postId, userId, httpServletRequest);
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
	public ClientResponse processLikes(Long userId, Long postId,HttpServletRequest httpServletRequest) throws Exception
	{
		logger.info("Likes Api Initiated");
		ClientResponse response = null;
		try {
			response = likePostService.processLike(userId, postId, httpServletRequest);
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
