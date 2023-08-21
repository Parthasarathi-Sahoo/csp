package com.rabo.csp.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Contact;


/**
 * OpenApiConfig configure Open API docs for csp
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@RequiredArgsConstructor
@Configuration
public class OpenApiConfig {
    private final OpenApiConstants openApiConstants;

    @Bean
    public OpenAPI cspOpenApi() {
        return new OpenAPI()
                .info(new Info().title(openApiConstants.getTitle())
                        .description(openApiConstants.getDescription())
                        .version(openApiConstants.getVersion())
                        .contact(getContact()));
    }

    private Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail(openApiConstants.getContact().getEmail());
        contact.setName(openApiConstants.getContact().getTeamName());
        return contact;
    }
}
