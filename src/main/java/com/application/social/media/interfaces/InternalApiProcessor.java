package com.application.social.media.interfaces;

import org.springframework.stereotype.Service;

import com.application.social.media.wrapper.ClientResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface InternalApiProcessor {

	public ClientResponse processAllUser(HttpServletRequest httpServletRequest) throws Exception;
	
	public ClientResponse processSingleUser(Long id, HttpServletRequest httpServletRequest)throws Exception;
	
}
