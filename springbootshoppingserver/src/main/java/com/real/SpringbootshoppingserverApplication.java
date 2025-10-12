package com.real;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootshoppingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootshoppingserverApplication.class, args);
	}

}
