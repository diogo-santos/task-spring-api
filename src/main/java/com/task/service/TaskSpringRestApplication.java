package com.task.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class TaskSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSpringRestApplication.class, args);
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.task.service"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(new ApiInfoBuilder()
						.title("Task API")
						.version("0.0.1")
						.description("A set of services to provide data access to tasks")
						.build());
	}
}