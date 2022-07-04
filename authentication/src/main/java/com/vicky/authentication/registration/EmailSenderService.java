package com.vicky.authentication.registration;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.vicky.authentication.exception.BusinessException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailSenderService {
	
	private final JavaMailSender javaMailSender;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
	
	public void sendMail(String to, String email) {
		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setFrom("vsv9071@gmail.com");
			helper.setTo(to);
			helper.setSubject("confirm your email");
			helper.setText(email, true);
			javaMailSender.send(mimeMessage);
			
		}catch(Exception e){
			LOGGER.error("ERROR in sending email");
			throw new BusinessException("error sending email");
		}
	}
	
}
