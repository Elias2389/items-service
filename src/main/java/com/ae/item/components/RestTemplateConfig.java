package com.ae.item.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("restClient")
    public RestTemplate provideRestClient() {
        return new RestTemplate();
    }
}
