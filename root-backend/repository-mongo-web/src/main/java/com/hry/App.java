package com.hry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration 
//@EnableJpaRepositories("com.hry.persistence.repository")
//@EnableMongoRepositories("com.hry.persistence.repository")
@SpringBootApplication
//@EnableAutoConfiguration
//@EntityScan("com.hry.persistence.model")
public class App implements CommandLineRunner{
	private static final Logger logger = LogManager.getLogger(App.class);

	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		logger.info("init repository...");
	}
}