package com.hry.core.common.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component("BaseMailSender")
public class BaseMailSender {
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	//@Autowired(required=true)
	JavaMailSender javaMailSender;
	
	public void sendMail(String from, String to, String subject, String body) {		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom(from);
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(body);
		
		logger.info("Sending...");	
		javaMailSender.send(mail);	
		logger.info("Done!");
	}
}