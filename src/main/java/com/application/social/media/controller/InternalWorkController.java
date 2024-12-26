package com.application.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.social.media.dto.email.EmailDto;
import com.application.social.media.interfaces.InternalApiProcessor;
import com.application.social.media.interfaces.SocialMediaApiProcessor;
import com.application.social.media.internal.Dao.UserRepo;
import com.application.social.media.service.Email.EmailService;
import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/internal")
public class InternalWorkController {

	@Autowired 
	private UserRepo userRepo;
	
	@Autowired
	private InternalApiProcessor internalApiProcessor;

	
	@Autowired
	private SocialMediaApiProcessor socialMediaApiProcessor;
	
	@Autowired 
	private EmailService emailService;
	
	
	@GetMapping("/user")
	public ClientResponse allUser(HttpServletRequest httpServletRequest)throws Exception
	{
		return internalApiProcessor.processAllUser(httpServletRequest);
	}
	@GetMapping(path = "/users" , params ="id")
	public ClientResponse user(@RequestParam("id") Long id,HttpServletRequest httpServletRequest) throws Exception
	{
		return internalApiProcessor.processSingleUser(id, httpServletRequest);
	}
	
	@PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDto details)
    {
        String status
            = emailService.sendSimpleMail(details);
 
        return status;
    }
}
