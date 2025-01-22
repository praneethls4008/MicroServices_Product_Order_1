package com.learn.microservices.order.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI GatewayAPI(){
        return new OpenAPI().info(new Info()
                        .title("API Gateway")
                        .description("Gateway api!")
                        .version("v0.0.1")
                        .license(new License().name("My apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("for more document click!")
                        .url("https://google.com"));
    }
}

