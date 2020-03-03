package com.simba.thread;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = {"com.simba"})
public class Start {

	public static void main(String[] args) {
		SpringApplication.run(Start.class,args);
	}
}
