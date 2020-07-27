package com.hry.core.common.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@Configuration
//@EnableJpaRepositories("com.hry.persistence.repository")
@SpringBootApplication
//@EnableAutoConfiguration
//@EntityScan("com.hry.persistence.model")
public class BaseApp extends SpringBootServletInitializer implements CommandLineRunner{
	private static final Logger log= LogManager.getLogger(BaseApp.class);
	
    @Override
	public void run(String... arg0) throws Exception {
        log.info("init base repositoy...");
	}
}

