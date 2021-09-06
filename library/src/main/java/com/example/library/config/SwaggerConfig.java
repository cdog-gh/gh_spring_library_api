package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("Swagger library")
            .description("This is sample library Server")
            .build();
    }
    @Bean
    public Docket apiDocket(){ //commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .securitySchemes(defTokenSchema())
            .apiInfo(this.apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.library"))
            .paths(PathSelectors.any())
            .build();
    }

    private List<? extends SecurityScheme> defTokenSchema() {
        List <ApiKey> tokenSchema = new ArrayList<>();
        tokenSchema.add(
            new ApiKey("jwt_access_token", "jwt_access_token", "header")
        );
        return tokenSchema;
    }
}
