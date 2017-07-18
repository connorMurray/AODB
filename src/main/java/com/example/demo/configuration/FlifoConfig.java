package com.example.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("flifo")
public class FlifoConfig {

    private String apiKey;
    private String aeroAPIEndPoint;

    public String getApiKey() {

        return apiKey;
    }

    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;
    }

    public String getAeroAPIEndPoint() {

        return aeroAPIEndPoint;
    }

    public void setAeroAPIEndPoint(String aeroAPIEndPoint) {

        this.aeroAPIEndPoint = aeroAPIEndPoint;
    }
}