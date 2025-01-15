package com.pickban;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.pickban.config.token.JwtProperties;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class PickbanApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PickbanApplication.class, args);
	}

}
