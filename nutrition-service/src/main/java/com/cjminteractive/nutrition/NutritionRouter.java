package com.cjminteractive.nutrition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class NutritionRouter {

	@Bean
	public RouterFunction<ServerResponse> route(NutritionHandler handler) {
		return RouterFunctions
			.route(RequestPredicates.GET("/api/v1/nutrients")
				.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
				handler::getNutrients
			);
	}
}
