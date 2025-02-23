package com.outsera_test.worst_movie_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@OpenAPIDefinition(
        info = @Info(
                title = "Golden Raspberry Awards Rest API",
                description = "REST APIs obtem os detalhes dos produtores que estão na categoria de pior filme",
                version = "v1",
                contact = @Contact(
                        name = "Gian milani"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.eazybytes.com"
                )
        )
)
public class WorstMovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorstMovieApiApplication.class, args);
    }

}
