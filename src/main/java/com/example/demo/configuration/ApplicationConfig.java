package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Configuration
@Import({FlifoConfig.class})
public class ApplicationConfig {

    @Autowired
    private FlifoConfig flifoConfig;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MultiValueMap<String, String> flifoApiKeyHeaders() throws Exception {
        MultiValueMap<String, String> restHeader = new LinkedMultiValueMap<>();
        restHeader.add("X-apiKey", flifoConfig.getApiKey());
        restHeader.add("Accept", "application/json");
        restHeader.add("User-Agent", "Apache HTTPClient");
        return restHeader;
    }
}