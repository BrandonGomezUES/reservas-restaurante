package com.reservas.restaurante.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Reservas para Restaurante")
                        .version("0.0.1-SNAPSHOT")
                        .description("Documentación de los endpoints para la gestión de clientes, mesas, turnos y reservas."));
    }
}