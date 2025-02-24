package com.outsera_test.worst_movie_api.core.service.impl;

import static java.util.stream.Collectors.partitioningBy;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerGrouper;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerIntervalCreator;
import com.outsera_test.worst_movie_api.core.service.producerMapper.WeightedAverageCalculator;
import com.outsera_test.worst_movie_api.core.service.producerMapper.impl.ResponseProducersAwardsBuilder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProducersAwardsMapperServiceImpl implements ProducersAwardsMapperService {

  private final ProducerGrouper producerGrouper;
  private final ProducerIntervalCreator producerIntervalCreator;
  private final WeightedAverageCalculator weightedAverageCalculator;
  private final ResponseProducersAwardsBuilder responseProducersAwardsBuilder;

  public ProducersAwardsMapperServiceImpl(ProducerGrouper producerGrouper,
      ProducerIntervalCreator producerIntervalCreator,
      WeightedAverageCalculator weightedAverageCalculator, ResponseProducersAwardsBuilder responseProducersAwardsBuilder) {
    this.producerGrouper = producerGrouper;
    this.producerIntervalCreator = producerIntervalCreator;
    this.weightedAverageCalculator = weightedAverageCalculator;
    this.responseProducersAwardsBuilder = responseProducersAwardsBuilder;
  }


  @Override
  public ResponseProducersAwards mapToResponseProducersAwards(
      List<ProducerAwardIntervalDomain> producersResults) {
    var groupedByProducer = producerGrouper.groupByProducer(producersResults);

    var producerIntervals = groupedByProducer.entrySet().stream()
        .map(producerIntervalCreator::createProducerAwardInterval)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    var weightedAverage = weightedAverageCalculator.calculateWeightedAverage(producerIntervals);

    var partitionedProducers = producerIntervals.stream().collect(
        partitioningBy(producer -> producer.getInterval() < weightedAverage)
    );

    return responseProducersAwardsBuilder.buildResponse(partitionedProducers);
  }
}