package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.MovieSearchService;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.ProducerAwardIntervalRepository;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.impl.ProducerAwardIntervalRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class MovieSearchServiceImpl implements MovieSearchService {

  private final ProducerAwardIntervalRepository producerAwardIntervalRepository;
  private final ProducersAwardsMapperService producersAwardsMapperService;

  public MovieSearchServiceImpl(
      ProducerAwardIntervalRepositoryImpl producerAwardIntervalRepositoryImpl,
      ProducersAwardsMapperService producersAwardsMapperService
  ) {
    this.producerAwardIntervalRepository = producerAwardIntervalRepositoryImpl;
    this.producersAwardsMapperService = producersAwardsMapperService;
  }

  @Override
  public ResponseProducersAwards getProducersAwards() {
    var producerAwardsInterval = producerAwardIntervalRepository.getProducerWithLongestAndFastestAwardsInterval();
    return producersAwardsMapperService.mapToResponseProducersAwards(producerAwardsInterval);
  }
}