package com.application.social.media.service.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.application.social.media.dto.email.EmailDto;

@Service
public class EmailServiceMethod {

	
	
	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") 
    private String sender;
 
  
    public String sendMail(String mail)
    {
    	 try {
    		 
    		 EmailDto details = new EmailDto();
	    	
	    	 details.setMsgBody("Your Account is Successfull Opened with this Mail. \n Thanks \n Social Media");
	    	 details.setSubject("New User");
    		
             SimpleMailMessage mailMessage = new SimpleMailMessage();

             mailMessage.setFrom(sender);
             mailMessage.setTo(mail);
             mailMessage.setText(details.getMsgBody());
             mailMessage.setSubject(details.getSubject());

             javaMailSender.send(mailMessage);
             return "Mail Sent Successfully...";
         }
         catch (Exception e) {
             return "Error while Sending Mail";
         }
    }
    
//    private EmailDto mailData(EmailDto data)
//    {
//    	EmailDto details = new EmailDto();
//    	details.setRecipient("");
//    	details.setMsgBody("Your Account is Successfull Opened with this Mail. \t\t Thanks \t Social Media");
//    	details.setSubject("New User");
//    	
//    	return details;
//    }
}
