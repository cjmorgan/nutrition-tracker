package com.cjminteractive.nutrition;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;

@Component
public class NutritionHandler {
	
	@Autowired
	private WebClient webClient;

	public Mono<ServerResponse> getNutrients(ServerRequest request) {
		Optional<String> query = request.queryParam("food");
		
		if (!query.isPresent()) {
			return ServerResponse.badRequest().build();
		}
		
		return webClient.post()
			.uri("/natural/nutrients")
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(new NutrientsPostBody(query.get()))
			.retrieve()
			.onStatus(HttpStatus::isError, error -> {
				if (error.statusCode().equals(HttpStatus.NOT_FOUND)) {
					return Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND));
				} else if (error.statusCode().equals(HttpStatus.UNAUTHORIZED)) {
					return Mono.error(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE));
				} else {
					return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));
				}
			})
			.bodyToMono(Object.class)
			.flatMap(body -> {
				return ServerResponse.ok()
					.bodyValue(body);
			});
	}
}
