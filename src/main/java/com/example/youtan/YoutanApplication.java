package com.example.youtan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@OpenAPIDefinition(
		info = @Info(
				title = "Youtan API",
				version = "1.0",
				description = "API para gerenciar tarefas"
		)
)
public class YoutanApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutanApplication.class, args);
	}

}
