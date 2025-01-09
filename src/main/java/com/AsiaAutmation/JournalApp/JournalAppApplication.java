package com.AsiaAutmation.JournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableTransactionManagement
@CrossOrigin
@EnableScheduling
public class JournalAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(JournalAppApplication.class, args);
	}

}
