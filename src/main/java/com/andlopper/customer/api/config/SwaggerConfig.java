package com.andlopper.customer.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIDefinition() {

        return new OpenAPI()
                .info(new Info().title("Gerenciamento de clientes")
                        .contact(new Contact().name("André Pereira").email("andrelopespereiraa@gmail.com"))
                        .description("Aplicação desenvolvida para gerenciamento de clientes.")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation().description("GitHub")
                        .url("https://github.com/andlopper/customer-api"));
    }
}
