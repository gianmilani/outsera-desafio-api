package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerGrouper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProducerGrouperImpl implements ProducerGrouper {

  @Override
  public Map<String, List<ProducerAwardIntervalDomain>> groupByProducer(
      List<ProducerAwardIntervalDomain> producers) {
    return producers.stream()
        .flatMap(producer -> stream(producer.getProducer().split("and|,"))
            .map(name -> ProducerAwardIntervalDomain.builder()
                .producer(name.trim())
                .interval(producer.getInterval())
                .previousWin(producer.getPreviousWin())
                .followingWin(producer.getFollowingWin())
                .build()))
        .collect(groupingBy(ProducerAwardIntervalDomain::getProducer));
  }
}
