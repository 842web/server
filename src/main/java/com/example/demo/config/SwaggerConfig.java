package com.example.demo.config;



import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
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

    private static final String REFERENCE = "Bearer 토큰 값";

    @Bean
    public OpenAPI openAPI() {

        String jwt = "JWT ";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );

        return new OpenAPI().addSecurityItem(securityRequirement).components(components);

    }

}