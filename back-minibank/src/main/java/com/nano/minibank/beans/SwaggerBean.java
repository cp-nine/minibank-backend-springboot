package com.nano.minibank.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerBean {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                "E Wallet API",
//                "Dokumentasi api e-wallet.",
//                "API-V1",
//                "www.ikhsan.com",
//                new Contact("Ikhsan Fauji", "www.ikhsan.com", "ikhsan3f@gmail.com"),
//                "Valid", "Valid", Collections.emptyList());
//    }

}

