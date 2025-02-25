package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerIntervalCreator;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntervalCreatorImpl implements ProducerIntervalCreator {

  @Override
  public ProducerAwardIntervalDomain createProducerAwardInterval(
      Entry<String, List<ProducerAwardIntervalDomain>> entry
  ) {
    var producer = entry.getKey();
    var intervals = entry.getValue();

    var result = intervals.stream()
        .collect(Collectors.teeing(
            minBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getPreviousWin)),
            maxBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getFollowingWin)),
            (minOpt, maxOpt) -> {
              if (minOpt.isPresent() && maxOpt.isPresent()) {
                var minInterval = minOpt.get();
                var maxInterval = maxOpt.get();
                int interval = maxInterval.getFollowingWin() - minInterval.getPreviousWin();
                if (interval > 0) {
                  return ProducerAwardIntervalDomain.builder()
                      .producer(producer)
                      .interval(interval)
                      .previousWin(minInterval.getPreviousWin())
                      .followingWin(maxInterval.getFollowingWin())
                      .build();
                }
              }
              return null;
            }
        ));

    return result;
  }
}