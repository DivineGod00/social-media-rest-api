package com.application.social.media.interfaces;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.application.social.media.wrapper.ClientRequest;
import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface SocialMediaApiProcessor {

	public ClientResponse processSignUp(ClientRequest request,HttpServletRequest httpServletRequest) throws Exception;
	
	public ClientResponse processSignIn(ClientRequest request,HttpServletRequest httpServletRequest) throws Exception;
	
	public ClientResponse processUser(Long id,HttpServletRequest httpServletRequest) throws Exception;
	
	public ClientResponse processPost(ClientRequest request,Long id, HttpServletRequest httpServletRequest) throws Exception;
	
	public ClientResponse processSinglePost(Long postId, Long userId,HttpServletRequest httpServletRequest) throws Exception;
	
}
