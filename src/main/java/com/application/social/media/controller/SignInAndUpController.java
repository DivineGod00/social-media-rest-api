package com.application.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/social-media")
public class SignInAndUpController {

	@Autowired
	private SocialMediaApiProcessor socialMediaApiProcessor;
	
	
	@PostMapping("/signup")
	private ClientResponse addUser(@RequestBody ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSignUp(request, httpServletRequest);
	}
	
	
	@PostMapping("/signIn")
	private ClientResponse login(@RequestBody ClientRequest request,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSignIn(request, httpServletRequest);
	}
	
	@GetMapping(path = "/signIn/user" , params ="id")
	private  ClientResponse user(@RequestParam("id") Long id,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processUser(id, httpServletRequest);
	}
	
}
