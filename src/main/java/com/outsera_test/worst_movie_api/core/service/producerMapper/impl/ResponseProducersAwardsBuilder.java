package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ResponseProducersAwardsBuilder {

  public ResponseProducersAwards buildResponse(
      Map<Boolean, List<ProducerAwardIntervalDomain>> partitionedProducers) {
    return ResponseProducersAwards.builder()
        .min(partitionedProducers.get(false))
        .max(partitionedProducers.get(true))
        .build();
  }
}