package com.caito.paymentchain.product.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author caito Vilas
 */

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("customers")
                .packagesToScan("com.caito.paymentchain.product")
                .build();
    }

    @Bean
    public OpenAPI springShopOpemapi(){
        return new OpenAPI()
                /*.addSecurityItem(new SecurityRequirement().addList(securityScemeName))
                .components(new Components())*/
                .info(new Info().title("Gestion Productos Paymentchain ")
                        .description("Gestor Productos  Paymentchain")
                        .version("v1.0"));

    }
}
