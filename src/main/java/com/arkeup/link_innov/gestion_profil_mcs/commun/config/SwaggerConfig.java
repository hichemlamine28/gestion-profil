package com.arkeup.link_innov.gestion_profil_mcs.commun.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by MahSalBouy on 20/04/2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {



    @Autowired
    private Environment environment;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arkeup.link_innov.gestion_profil_mcs.infrastructure"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(
                		 Arrays.asList(new ParameterBuilder()
                		            .name("Authorization")
                		            .defaultValue("Bearer {{Token}}")
                		            .description("Authorization header")
                		            .modelRef(new ModelRef("Token"))
                		            .parameterType("header")
                		            .required(true)
                		            .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                environment.getProperty("application.name") + " API",
                environment.getProperty("application.description"),
                environment.getProperty("build.version") + " " + environment.getProperty("build.timestamp"),
                "Terms of service",
                new Contact("MSB", "www.arkeup.com", "contact@arkeup.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
