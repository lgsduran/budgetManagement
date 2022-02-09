package br.com.alura.budgetManagement.configuration;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Budget Management", version = "v1"))
@SecurityScheme(
    name = "bearerAuth",
    type = HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class OpenApi30Config {

}
