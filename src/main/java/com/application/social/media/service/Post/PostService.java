package com.application.social.media.service.Post;

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

import com.application.social.media.Dao.PostRepo;
import com.application.social.media.Dao.SignInRepo;
import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.post.PostDto;
import com.application.social.media.model.master.posts.Posts;
import com.application.social.media.model.master.users.UserCredentials;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	private SignInRepo signInRepo;
	
	@Autowired
	private PostRepo postRepo;
	
	public ClientResponse processPost(PostDto post, Long id ,HttpServletRequest httpServletRequest) throws Exception
	{
		Map<String,String> data=new HashMap<>();
		UserCredentials user = signInRepo.findById(id).orElse(null);
		if(user == null)
		{
			return failure(StatusCode.NOT_FOUND, user,Constants.USER_NOT_FOUND);
		}
		if(post != null)
		{
			Posts savePost = postData(post,id);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.query("id={id}")
					.buildAndExpand(savePost.getId())
					.toUri();  
			logger.info("path "+location);
			data.put("url", location.toString());
			return success(data,"User Saved Successfully");
	
		}
		return failure(StatusCode.NOT_FOUND, null,"all data is null");
	}
	
	public ClientResponse  processSinglePost(Long postId, Long userId, HttpServletRequest httpServletRequest) throws Exception
	{
		ClientResponse response = new ClientResponse();
		Map<String,String> data=new HashMap<>();
		Map<String,Object> userData=new HashMap<>();
		
		
		UserCredentials user = signInRepo.findById(userId).orElse(null);
		if (user == null) {
		        response = failure(StatusCode.NOT_FOUND, null, Constants.USER_NOT_FOUND);
		        return response;
		    
		}
		Posts post = postRepo.findById(postId).orElse(null);
	    if (post == null) {
	        response = failure(StatusCode.NOT_FOUND, null, Constants.POST_NOT_FOUND);
	        return response;
	        
	    }
	    if (!post.getUserId().getId().equals(userId)) {
	        response = failure(StatusCode.NOT_FOUND, null, Constants.POST_NOT_FOUND_FOR_THIS_USER);
	        return response;
	    }
	    
	    data.put("name", post.getUserId().getUserId().getName());
	    data.put("username", post.getUserId().getUsername());
	    data.put("description", post.getDescription());
	    data.put("likes", post.getLikes().toString());
	    data.put("comments", post.getComments().toString());
	    data.put("shares", post.getShares().toString());
	   
	    PostDto posts = CommonUtilities.extractObject(data,PostDto.class);
	    
	    logger.info("post : "+posts);
	    
	   
	    
	   
	    response = success(posts, "Successfully Fetched.");
		return response;
	}
	
	
	private Posts postData(PostDto post, Long id)
	{
		Posts savePost = new Posts();
		UserCredentials user = signInRepo.findById(id).orElse(null);
		savePost.setDescription(post.getDescription());
		
		savePost.setUserId(user);
		postRepo.save(savePost);
		return savePost;
	}
}
