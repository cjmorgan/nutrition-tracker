package com.cjminteractive.nutrition;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@ConfigurationProperties(prefix="nutritionix.api")
public class NutritionixConfiguration {

	private String apiKey;
	private String appId;
	private String rootUrl;
	
	public String getApiKey() {
		return apiKey;
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	
	public WebClient getClient() {
		return WebClient.builder()
			.baseUrl(rootUrl)
			.defaultHeader("x-app-id", appId)
			.defaultHeader("x-app-key", apiKey)
			.build();
	}
}
