package sms;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmSerApplication {

	public static void main(String[] args) {
		 SpringApplication app = new SpringApplication(SmSerApplication.class);
	        String port = System.getenv("PORT");   // get port from Render
	        if (port == null) port = "8080";       // default for local testing
	        app.setDefaultProperties(Collections.singletonMap("server.port", port));
	        app.run(args);
	}

}
