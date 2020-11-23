package com.pon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//fallback cache provider is the default cache provider of springboot
@EnableCaching
public class SbCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbCachingApplication.class, args);
	}

}
