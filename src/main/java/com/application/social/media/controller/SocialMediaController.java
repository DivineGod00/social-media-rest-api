package com.application.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.social.media.dto.email.EmailDto;
import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.service.Email.EmailService;
import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/social-media")
public class SocialMediaController {

	@Autowired
	private SocialMediaApiProcessor socialMediaApiProcessor;
	
	@Autowired 
	private EmailService emailService;
	
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
	
	@PostMapping(path = "/user/{userId}/posts")
	public ClientResponse post(@RequestBody ClientRequest request, @PathVariable Long userId,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processPost(request, userId,httpServletRequest);
	}
	
	@GetMapping(path = "/user/{userId}/posts" , params ="postId")
	public ClientResponse post(@RequestParam("postId") Long postId, @PathVariable Long userId,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSinglePost(postId, userId, httpServletRequest);
	}
	
	@PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDto details)
    {
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
	
	 @PostMapping("/sendMailWithAttachment")
	    public String sendMailWithAttachment(
	        @RequestBody EmailDto details)
	    {
	        String status
	            = emailService.sendMailWithAttachment(details);
	 
	        return status;
	    }
	
	 
	
}
