package com.nursultanturdaliev.moneytransferapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.collect.Lists.newArrayList;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableSwagger2
public class MoneyTransferAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyTransferAppApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(200)
                                        .message("successful request")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("bad request")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("resource not found")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("internal server error")
                                        .build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder()
                                        .code(201)
                                        .message("user created")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("bad request")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(405)
                                        .message("method not allowed")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("internal server error")
                                        .build()))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(204)
                                        .message("no content")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("resource not found")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("internal server error")
                                        .build()))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(400)
                                        .message("bad request")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(404)
                                        .message("resource not found")
                                        .build(),
                                new ResponseMessageBuilder()
                                        .code(500)
                                        .message("internal server error")
                                        .build()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "MONEY TRANSFER APP REST API",
                "This is the main rest api documentation.",
                "API V1.0.0",
                "Terms of service",
                new Contact("Nursultan Turdaliev", "www.kapusta.com", "contact@kapusta.com"),
                "GPL 3.0", "https://www.gnu.org/licenses/gpl-3.0.en.html", Collections.emptyList());
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}