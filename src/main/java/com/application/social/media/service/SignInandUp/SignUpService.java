package com.application.social.media.service.SignInandUp;

import static com.application.social.media.common.methods.CommonUtilities.failure;
import static com.application.social.media.common.methods.CommonUtilities.success;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.application.social.media.Dao.SignInRepo;
import com.application.social.media.Dao.SignUpRepo;
import com.application.social.media.common.methods.CommonUtilities;
import com.application.social.media.dto.signUp.SignUpDto;
import com.application.social.media.model.master.users.User;
import com.application.social.media.model.master.users.UserCredentials;
import com.application.social.media.service.Email.EmailServiceMethod;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.StatusCode;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class SignUpService {

	private static final Logger logger = LoggerFactory.getLogger(SignUpService.class);
	
	@Autowired
	private CommonUtilities commonUtilities;
	
	@Autowired
	private SignUpRepo signUpRepo;
	
	@Autowired
	private SignInRepo signInRepo;
	
	@Autowired
	private EmailServiceMethod emailServiceMethod;
	
	
	public ClientResponse processSignUp(SignUpDto request, HttpServletRequest httpServletRequest)throws Exception 
	{
		if(request != null)
		{
			boolean isNotNull = checkingUserIsAlredyExist(request.getContactNo(), request.getEmail());
			Map<String,String> data=new HashMap<>();
			
			if(isNotNull)
			{
				
				logger.info("Details of User : "+request);
				if(request.getFirstName().length() < 2 || request.getLastName().length() < 2)
				{			 
					data.put("error", request.getFirstName()+" Name char is more than 2.");
					return failure(StatusCode.VALIDATION_ERROR, data, "Name char is more than 2.");
				}
				
				LocalDate bd = LocalDate.parse(request.getBirthDate());
				if (bd.isEqual(LocalDate.now())) {
				    return failure(StatusCode.VALIDATION_ERROR, request, "Birth Date should not be today's date.");
				}
				boolean isMobileNoExist = mobileNoExist(request.getContactNo());
				boolean isEmailExist = emailExist(request.getEmail());
				
				if(!isMobileNoExist && !isEmailExist)
				{
					data.put("mobileNo", request.getContactNo());
					data.put("mail", request.getEmail());
					return failure(StatusCode.MATCH_CREDENTIALS, data,"User Exist with this Email and MobileNo.");
				}
				if(!isMobileNoExist)
				{
					data.put("mobileNo", request.getContactNo());
					return failure(StatusCode.MATCH_CREDENTIALS, data,"User Exist with this Mobile No.");
				}
				if(!isEmailExist)
				{
					data.put("mail", request.getEmail());
					return failure(StatusCode.MATCH_CREDENTIALS, data,"User Exist with this Email.");
				}
				
				if(request.getUsername().length() < 2)
				{
					data.put("error", request.getUsername()+" Username  char is more than 2.");
					return failure(StatusCode.VALIDATION_ERROR, data, "Name char is more than 2.");
				}
				if(request.getUsername().length() > 10)
				{
					data.put("error", request.getUsername()+" Username  char is not more than 10.");
					return failure(StatusCode.VALIDATION_ERROR, data, "Name char is more than 10.");
				}
				boolean isUsername = usernameExist(request.getUsername());
				if(!isUsername)
				{
					data.put("username", request.getUsername());
					return failure(StatusCode.MATCH_CREDENTIALS, data,"Username Exist.");
				}
				User userRequest = addUser(request);
				
				UserCredentials ucSave = addCredentails(request);
				URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.query("id={id}")
						.buildAndExpand(userRequest.getId())
						.toUri();  
				logger.info("path "+location);
				data.put("url", location.toString());
				
				return success(data,"User Saved Successfully");
			}
			
			data.put("mobileNo", null);
			data.put("mail", null);
			return failure(StatusCode.NOT_FOUND, data,"null");
			}
		
		return failure(StatusCode.NOT_FOUND, null,"all data is null");
	}
	
	private User addUser(SignUpDto user)
	{
		User addUser = new User();
		addUser.setName(user.getFirstName()+ " "+user.getLastName());
//		LocalDate birthDate = LocalDate.parse(user.getBirthDate());
		addUser.setBirthDate(LocalDate.parse(user.getBirthDate()));
		emailServiceMethod.sendMail(user.getEmail());
		addUser.setEmail(user.getEmail());
		addUser.setContactNo(user.getContactNo());
		signUpRepo.save(addUser);
		return addUser;
	}
	
	private UserCredentials addCredentails(SignUpDto user)
	{
		UserCredentials uc = new UserCredentials();
		uc.setUsername(user.getUsername());
		uc.setPassword(user.getPassword());
		User us = signUpRepo.findByName(user.getFirstName()+ " "+user.getLastName());
		
		uc.setUserId(us);
		
		signInRepo.save(uc);
		
		return uc;
	}
	
	private boolean checkingUserIsAlredyExist(String mobileNo, String mail) throws Exception {
		if (commonUtilities.isEmptyOrNull(mobileNo) || commonUtilities.isEmptyOrNull(mail)) {
			return false;
		}
		if (commonUtilities.isEmptyOrNull(mobileNo) && commonUtilities.isEmptyOrNull(mail)) {
			return false;
		}
		
		return true;
	}
	
	private boolean mobileNoExist(String mobileNo) throws Exception {
	
	        User phno = signUpRepo.findByContactNo(mobileNo);
	        if (phno != null && mobileNo.equals(phno.getContactNo())) {
	            logger.info("------ Mobile Matched Try with other ------");
	            return false;
	        } else {
	      
	            return true;
	        }
	}
	
	private boolean emailExist(String mail) throws Exception {
		
		
        User email = signUpRepo.findByEmail(mail);
        if (email != null && mail.equals(email.getEmail())) {
            logger.info("------ Email Matched Try with other ------");
            return false;
        } else {
            return true;
        }
	}
	private boolean checkingSignInDetails(String username) throws Exception {
		if (commonUtilities.isEmptyOrNull(username)) {
			return false;
		}
		
		
		return true;
	}
	private boolean usernameExist(String username) throws Exception {

        UserCredentials name = signInRepo.findByUsername(username);
        if (name != null && username.equals(name.getUsername())) {
            logger.info("------ Username Matched Try with other ------");
            return false;
        } else {
            return true;
        }
	}
}
