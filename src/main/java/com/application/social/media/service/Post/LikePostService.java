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
import com.application.social.media.internal.Dao.LikesRepo;
import com.application.social.media.model.master.posts.Likes;
import com.application.social.media.model.master.posts.Posts;
import com.application.social.media.model.master.users.UserCredentials;
import com.application.social.media.service.SignInandUp.SignUpService;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;


@Service
public class LikePostService {
	private static final Logger logger = LoggerFactory.getLogger(LikePostService.class);
	
	@Autowired
	private SignUpService signUpService;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private LikesRepo likeRepo;
	
	@Autowired
	private SignInRepo signInRepo;
	
	
	public ClientResponse processLike(Long userId,Long postId,HttpServletRequest httpServletRequest) throws Exception
	{

		ClientResponse response = new ClientResponse();
		Map<String, String> data = new HashMap<>();
		
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
	   
	    logger.info("user : "+user);
	    logger.info("post :"+post);
	    Likes saveLike = new Likes();
	    Likes postID = likeRepo.findByPostIdAndUserId(post.getId(), user.getId());
	    if(postID == null)
	    {

	    	saveLike.setPostId(post.getId());
		    saveLike.setUserId(user.getId());
	    	likeRepo.save(saveLike);   
	  	    Long likes = post.getLikes();
	  	    Long like = likes+1;
	  	    post.setLikes(like);
	  	    postRepo.save(post);
	  	  data.put("message", "Like By "+user.getUsername());
	  	 
	    }
	    else if(postID.getPostId() == post.getId() || postID.getUserId() == user.getId())
	    {
	    	Long likes = post.getLikes();
		    Long like = likes-1;
		    if(post.getLikes() > 0)
		    {
		    	post.setLikes(like);
		    }
		    postRepo.save(post);
		    likeRepo.delete(postID);
		    data.put("message", "Dislike By "+user.getUsername());
		    
	    }
	    else
	    {
	    	 response = failure(StatusCode.NOT_FOUND, null, Constants.FAILURE);
		        return response;	
	    }
//	    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/likes")
//				.query("postId={postId}")
//				.buildAndExpand(postId)
//				.toUri();
//	    logger.info("path "+location);
//		data.put("url", location.toString());
	   
		return success(data,"User Saved Successfully");
		
	}
	
}
