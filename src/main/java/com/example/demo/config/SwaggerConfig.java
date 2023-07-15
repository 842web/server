package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "8421 API 명세서",
                description = "UMC 4기 프로젝트",
                version = "v1",
                contact = @Contact(
                        name = "김유신",
                        email = "sample@email.co.kr"
                )
        ),
        servers = {
                @Server(url = "/", description = "Default")
        }
)

@Configuration
public class SwaggerConfig {
}