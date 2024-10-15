package com.example.producerkafkatest.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Grain Service",
                version = "1.0",
                description = "Grain Service",
                contact = @Contact(
                        name = "Mihail Kuznetsov",
                        email = "smith80java@gmail.ru"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://opensource.org/licenses/mit-license.php"
                )
        )
)

public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
