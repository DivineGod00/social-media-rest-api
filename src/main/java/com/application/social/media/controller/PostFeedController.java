package com.application.social.media.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class PostFeedController {

	@Autowired
	private SocialMediaApiProcessor socialMediaApiProcessor;
	
	
	
	
	@PostMapping(path = "/user/{userId}/posts")
	private ClientResponse post(@RequestBody ClientRequest request, @PathVariable Long userId,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processPost(request, userId,httpServletRequest);
	}
	
	@GetMapping(path = "/user/{userId}/posts" , params ="postId")
	private ClientResponse post(@RequestParam("postId") Long postId, @PathVariable Long userId,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processSinglePost(postId, userId, httpServletRequest);
	}
	
	@PostMapping(path = "/user/post/likes")
	private ClientResponse likes(@RequestParam Long userId, @RequestParam Long postId,HttpServletRequest httpServletRequest) throws Exception
	{
		return socialMediaApiProcessor.processLikes(userId, postId, httpServletRequest);
	}

}
