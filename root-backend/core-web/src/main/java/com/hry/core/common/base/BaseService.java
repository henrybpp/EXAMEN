package com.hry.core.common.base;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.hry.core.common.util.Constantes;
import com.hry.core.common.wrapper.Response;

public abstract class BaseService {
	private static final Logger logger = LogManager.getLogger(BaseService.class);
		
	@Value("${user.default.pass}")
	private String defaultUser;
	
	@Value("${user.default.email}")
	private String defaultEmail;
			
	@Autowired
	public HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	/*
	@Autowired(required=true)
	PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("jdbcScheduler")
	public Scheduler jdbcScheduler;
	*/
	
	@PostConstruct
	void init(){}	

	public Integer getStatusOk() {
		return new Integer(200);
	}
	
	public String getPathApi() {
		return request.getRequestURI();
	}
	
	public Integer getStatusError() {
		return response.getStatus();
	}
	
	public <T> Response<T> getResponse() {
		Response<T> response=new Response<>();
		response.setPath(getPathApi());
		response.setStatus(getStatusOk());
		response.setStatusText(Constantes.STATUS_TEXT_OK);
		response.setMessage(Constantes.OPERACION_OK);
		return response;
	}
	
	@Autowired(required=true)
	private JavaMailSender javaMailSender;
	public void sendEmail(String to, String subject, String body) throws InterruptedException {	
		logger.info("start send email");
		//Thread.sleep(7000);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(defaultEmail);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
		logger.info("end send email");
	}
	
	/*
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	*/
}