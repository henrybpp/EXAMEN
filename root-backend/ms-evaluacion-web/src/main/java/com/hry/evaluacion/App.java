package com.hry.evaluacion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;

import com.hry.core.common.base.BaseApp;

//@SpringBootApplication
public class App extends BaseApp{
	private static final Logger log= LogManager.getLogger(App.class);
	
    public static void main( String[] args ){
    	SpringApplication.run(App.class, args);
    }
    
    
    @Override
	public void run(String... arg0) throws Exception {
        log.info("init producer ms...");
	} 
}
