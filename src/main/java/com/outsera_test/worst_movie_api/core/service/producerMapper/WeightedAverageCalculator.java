package com.outsera_test.worst_movie_api.core.service.producerMapper;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import java.util.List;

public interface WeightedAverageCalculator {

  double calculateWeightedAverage(List<ProducerAwardIntervalDomain> producers);
}
