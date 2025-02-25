package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerGrouper;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerIntervalCreator;
import com.outsera_test.worst_movie_api.core.service.producerMapper.impl.ResponseProducersAwardsBuilder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProducersAwardsMapperServiceImpl implements ProducersAwardsMapperService {

  private final ProducerGrouper producerGrouper;
  private final ProducerIntervalCreator producerIntervalCreator;
  private final ResponseProducersAwardsBuilder responseProducersAwardsBuilder;

  public ProducersAwardsMapperServiceImpl(ProducerGrouper producerGrouper,
      ProducerIntervalCreator producerIntervalCreator,
      ResponseProducersAwardsBuilder responseProducersAwardsBuilder) {
    this.producerGrouper = producerGrouper;
    this.producerIntervalCreator = producerIntervalCreator;
    this.responseProducersAwardsBuilder = responseProducersAwardsBuilder;
  }

  @Override
  public ResponseProducersAwards mapToResponseProducersAwards(
      List<ProducerAwardIntervalDomain> producersResults
  ) {

    var groupedByProducer = producerGrouper.groupByProducer(producersResults);

    var producerIntervals = groupedByProducer.entrySet()
        .stream()
        .map(producerIntervalCreator::createProducerAwardInterval)
        .filter(Objects::nonNull)
        .collect(Collectors.teeing(
            Collectors.maxBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getInterval)),
            Collectors.minBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getInterval)),
            this::createMaxMinMap
        ));

    return responseProducersAwardsBuilder.buildResponse(producerIntervals);
  }

  private Map<Boolean, List<ProducerAwardIntervalDomain>> createMaxMinMap(
      Optional<ProducerAwardIntervalDomain> max, Optional<ProducerAwardIntervalDomain> min) {
    Map<Boolean, List<ProducerAwardIntervalDomain>> result = new HashMap<>();
    result.put(true, max.stream().toList());
    result.put(false, min.stream().toList());
    return result;
  }
}