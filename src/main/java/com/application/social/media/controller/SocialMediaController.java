package com.application.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/social-media")
public class SocialMediaController {

	@Autowired
	private SocialMediaApiProcessor socialMediaApiProcessor;
	
	@PostMapping("/signup")
	public ClientResponse addUser(@RequestBody ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSignUp(request, httpServletRequest);
	}
	
	
	@PostMapping("/signIn")
	public ClientResponse login(@RequestBody ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSignIn(request, httpServletRequest);
	}
	
	@PostMapping(path = "/user" , params ="id")
	public ClientResponse user(@RequestParam("id") Long id,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processUser(id, httpServletRequest);
	}
	
	
	
	 
	
}
