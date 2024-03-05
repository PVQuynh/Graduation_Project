package com.example.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching
@EnableAsync
@OpenAPIDefinition(
		info = @Info(
				title = "VN GroupBy API",
				version = "1.0.0",
				description = "Building VN GroupBy e-commerce platform",
				contact = @Contact(
						name = "Phạm Văn Quỳnh",
						email = "quynh.pv193074@sis.hust.edu.vn"
				)
		),
		security = @SecurityRequirement(
				name = "bearerAuth"
		)
)
@SecurityScheme(
		name = "bearerAuth",
		description = "JWT auth description PVQ",
		scheme = "bearer",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
@EnableFeignClients
public class UserServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		;
	}

}
