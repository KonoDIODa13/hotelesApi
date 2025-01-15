package com.example.hotelesapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("HotelesAPI")
                        .description("API de Hoteles y Habitaciones")
                        .contact(new Contact()
                                .name("yo")
                                .email("mibinding")
                                .url("mimimimimi"))
                        .version("1.0"));
    }
}
