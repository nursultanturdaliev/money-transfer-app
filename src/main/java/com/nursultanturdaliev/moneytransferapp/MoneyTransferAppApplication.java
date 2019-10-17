package com.nursultanturdaliev.moneytransferapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class MoneyTransferAppApplication {

    public static final String   RECORD_CONFLICT = "RECORD_CONFLICT_CONSTANT";

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferAppApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Money Transfer App REST API",
                "This is the official REST API documentation",
                "v1.0.0",
                "Terms of Service",
                new Contact("Nursultan Turdaliev", "kapusta.com", "contact@kapusta.com"),
                "License of API",
                "API License URL",
                Collections.emptyList()
        );
    }

}