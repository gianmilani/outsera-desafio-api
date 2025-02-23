package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import static java.util.Arrays.stream;

@Service
public class ProducersAwardsMapperServiceImpl implements ProducersAwardsMapperService {

  @Override
  public ResponseProducersAwards mapToResponseProducersAwards(
      List<ProducerAwardIntervalDomain> producersResults) {
    var groupedByProducer = groupByProducer(producersResults);

    var producerIntervals = groupedByProducer.entrySet().stream()
        .map(this::createProducerAwardInterval)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    var weightedAverage = calculateWeightedAverage(producerIntervals);

    var partitionedProducers = producerIntervals.stream()
        .collect(Collectors.partitioningBy(producer -> producer.getInterval() < weightedAverage));

    return buildResponse(partitionedProducers);
  }

  private Map<String, List<ProducerAwardIntervalDomain>> groupByProducer(
      List<ProducerAwardIntervalDomain> producers) {
    return producers.stream()
        .flatMap(producer -> stream(producer.getProducer().split("and|,"))
            .map(name -> ProducerAwardIntervalDomain.builder()
                .producer(name.trim())
                .interval(producer.getInterval())
                .min(producer.getMin())
                .max(producer.getMax())
                .build()))
        .collect(Collectors.groupingBy(ProducerAwardIntervalDomain::getProducer));
  }

  private ProducerAwardIntervalDomain createProducerAwardInterval(
      Entry<String, List<ProducerAwardIntervalDomain>> entry
  ) {
    var producer = entry.getKey();
    var intervals = entry.getValue();

    var minYearOpt = intervals.stream().mapToInt(ProducerAwardIntervalDomain::getMin).min();
    var maxYearOpt = intervals.stream().mapToInt(ProducerAwardIntervalDomain::getMax).max();

    if (minYearOpt.isPresent() && maxYearOpt.isPresent()) {
      var minYear = minYearOpt.getAsInt();
      var maxYear = maxYearOpt.getAsInt();
      var interval = maxYear - minYear;

      if (interval > 0) {
        return ProducerAwardIntervalDomain.builder()
            .producer(producer)
            .interval(interval)
            .min(minYear)
            .max(maxYear)
            .build();
      }
    }
    return null;
  }

  private double calculateWeightedAverage(List<ProducerAwardIntervalDomain> producers) {
    var totalWeightedInterval = 0;
    var totalMaxYears = 0;
    for (ProducerAwardIntervalDomain producer : producers) {
      totalWeightedInterval += producer.getInterval() * producer.getMax();
      totalMaxYears += producer.getMax();
    }
    return totalMaxYears == 0 ? 0 : totalWeightedInterval / totalMaxYears;
  }

  private ResponseProducersAwards buildResponse(
      Map<Boolean, List<ProducerAwardIntervalDomain>> partitionedProducers) {
    return ResponseProducersAwards.builder()
        .min(partitionedProducers.get(true))
        .max(partitionedProducers.get(false))
        .build();
  }
}