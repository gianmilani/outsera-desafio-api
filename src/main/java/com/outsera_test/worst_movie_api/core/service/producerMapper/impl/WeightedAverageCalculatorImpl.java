package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.service.producerMapper.WeightedAverageCalculator;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WeightedAverageCalculatorImpl implements WeightedAverageCalculator {

  @Override
  public double calculateWeightedAverage(List<ProducerAwardIntervalDomain> producers) {
    var totalWeightedInterval = 0;
    var totalMaxYears = 0;
    for (ProducerAwardIntervalDomain producer : producers) {
      totalWeightedInterval += producer.getInterval() * producer.getFollowingWin();
      totalMaxYears += producer.getFollowingWin();
    }
    return totalMaxYears == 0 ? 0 : totalWeightedInterval / totalMaxYears;
  }
}
