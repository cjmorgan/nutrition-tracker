package com.cjminteractive.nutrition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class NutritionApplication {

	@Autowired
	private NutritionixConfiguration nutritionixConfig;
	
	public static void main(String[] args) {
		SpringApplication.run(NutritionApplication.class, args);
	}
	
	@Bean
	public WebClient getWebClient() {
		return nutritionixConfig.getClient();
	}

}
