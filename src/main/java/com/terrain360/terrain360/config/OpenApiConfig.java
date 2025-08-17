package com.terrain360.terrain360.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI terrain360OpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Terrain360 API")
                        .description("API documentation for Terrain360 application")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Terrain360 Support")
                                .email("support@terrain360.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}