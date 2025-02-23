package com.outsera_test.worst_movie_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class WorstMovieApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorstMovieApiApplication.class, args);
    }

}
