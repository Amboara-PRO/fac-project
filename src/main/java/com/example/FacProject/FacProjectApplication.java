package com.example.FacProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FacProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacProjectApplication.class, args);
	}

}
