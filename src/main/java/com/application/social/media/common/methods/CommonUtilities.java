package com.application.social.media.common.methods;

import static com.application.social.media.common.methods.CommonUtilities.failure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.social.media.Dao.SignUpRepo;
import com.application.social.media.internal.Dao.UserCredentialsRepo;
import com.application.social.media.model.master.User;
import com.application.social.media.model.master.UserCredentials;
import com.application.social.media.wrapper.ClientResponse;
import com.application.social.media.wrapper.Constants;
import com.application.social.media.wrapper.StatusCode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CommonUtilities {
	
	private Logger logger = LoggerFactory.getLogger(CommonUtilities.class);
	
	@Autowired
	private UserCredentialsRepo userCredentialsRepo;
	
	@Autowired
	private SignUpRepo signUpRepo;
	
	public static ClientResponse success(Object data, String message) {
		return ClientResponse.builder().statusCode(StatusCode.SUCCESS).data(data).status(Constants.SUCCESS)
				.message(message).build();
	}

	public static ClientResponse failure(Integer statusCode, Object data, String message) {
		return ClientResponse.builder().statusCode(statusCode).data(data).status(Constants.FAILURE).message(message)
				.build();
	}
	
	
	
	public static String convertObjectToJsonString(Object obj)throws Exception
	{
		if(obj == null){
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
	
	public static <T> T extractObject(Object object, Class<T> type) {
		return new ObjectMapper().convertValue(object, type);
	}
	public boolean isEmptyOrNull(String... string) {
		for (String s : string) {
			if ((null == s) || (s.isEmpty()))
				return true;
		}
		return false; 
	}

	public boolean isEqualsOrNot(String name, String password) {
		UserCredentials credentials = userCredentialsRepo.findByUsernameAndPassword(name, password);
		if (credentials != null && name.equals(credentials.getUsername())
				&& password.equals(credentials.getPassword())) {
			logger.info("--------- Matched Credentials --------");
			return true;
		}

		logger.info("---------- Not Matched Credentials ---------");
		return false;
	}
	public boolean checkingCredentials(String name, String password) throws Exception {
		if ((isEmptyOrNull(name) && isEmptyOrNull(password)) || isEmptyOrNull(name) || isEmptyOrNull(password)) {
			return false;
		}
		boolean isValidNamePassword = isEqualsOrNot(name, password);
		return isValidNamePassword;
	}
	
	public boolean checkingUserIsAlredyExist(String mobileNo, String mail) throws Exception {
		if (isEmptyOrNull(mobileNo) || isEmptyOrNull(mail)) {
			return false;
		}
		if (isEmptyOrNull(mobileNo) && isEmptyOrNull(mail)) {
			return false;
		}
		logger.info(" mobileNo : "+mobileNo+ " mail : "+mail);
		boolean isValidMobileNo = mobileNoExist( mobileNo );
		logger.info(" isValidMobileNo :"+isValidMobileNo);
		boolean isValidEmail = emailExist(mail);
		logger.info(" isValidEmail :"+isValidEmail);
		
		if(isValidMobileNo && isValidEmail)
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	
	public boolean mobileNoExist(String mobileNo) throws Exception {
	
	        User phno = signUpRepo.findByContactNo(mobileNo);
	        if (phno != null && mobileNo.equals(phno.getContactNo())) {
	            logger.info("------ Mobile Matched Try with other ------");
	            return false;
	        } else {
	      
	            return true;
	        }
	}
	
	public boolean emailExist(String mail) throws Exception {
		
		
        User email = signUpRepo.findByEmail(mail);
        if (email != null && mail.equals(email.getEmail())) {
            logger.info("------ Email Matched Try with other ------");
            return false;
        } else {
            return true;
        }
	}

}
