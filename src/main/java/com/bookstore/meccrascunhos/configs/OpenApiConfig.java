package com.bookstore.meccrascunhos.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyEcclesia")
                        .description("MyEcclesia é uma API baseada em Spring que busca facilitar na gestão" +
                        "e administração de igrejas e congregações.")
                        .version("1")
                        .termsOfService("https://github.com/floresdecarbono")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/")));
    }

}
