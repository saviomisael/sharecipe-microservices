package com.github.saviomisael.authub.adapter.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI().info(Info().title("Authub Swagger Docs").description("Documentation of authub REST API").version("1.0.0"))
    }
}