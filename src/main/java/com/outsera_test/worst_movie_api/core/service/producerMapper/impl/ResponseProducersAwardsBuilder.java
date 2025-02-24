package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ResponseProducersAwardsBuilder {

  public ResponseProducersAwards buildResponse(
      Map<String, ProducerAwardIntervalDomain> partitionedProducers) {
    return ResponseProducersAwards.builder()
        .min(partitionedProducers.get("min"))
        .max(partitionedProducers.get("max"))
        .build();
  }
}