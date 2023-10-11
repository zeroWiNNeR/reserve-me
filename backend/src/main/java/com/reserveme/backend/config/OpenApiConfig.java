package com.reserveme.backend.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(name = "Aleksei", email = "vekovshinin.ayu@gmail.com"),
//                description = "RM Application backend",
//                title = "RM Application backend",
//                version = "1.0"
//        ),
//        servers = {
//                @Server(description = "Local ENV", url = "http://localhost:8080")
//        },
//        security = {
//                @SecurityRequirement(name = "bearerAuth")
//        }
//)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl("http://localhost:8080");
		devServer.setDescription("Server URL in Development environment");

		Contact contact = new Contact();
		contact.setName("Aleksei");
		contact.setEmail("vekovshinin.ayu@gmail.com");

		Info info = new Info()
				.title("RM Application backend")
				.version("1.0")
				.contact(contact)
				.description("RM Application backend");

		SecurityRequirement security = new SecurityRequirement();
		security.addList("bearerAuth");

		return new OpenAPI().info(info).servers(List.of(devServer)).security(List.of(security));
	}

}
