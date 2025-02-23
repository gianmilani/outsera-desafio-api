package com.outsera_test.worst_movie_api.controller;

import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.MovieSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producers/awards")
public class ProducersAwardsController {

    private final MovieSearchService movieSearchService;

    public ProducersAwardsController(MovieSearchService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping
    public ResponseEntity<ResponseProducersAwards> getProducers() {
        ResponseProducersAwards producersAwards = movieSearchService.getProducersAwards();
        return ResponseEntity.ok(producersAwards);
    }
}
