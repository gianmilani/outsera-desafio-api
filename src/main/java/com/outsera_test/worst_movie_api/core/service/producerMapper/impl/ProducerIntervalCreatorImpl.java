package com.outsera_test.worst_movie_api.core.service.producerMapper.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerIntervalCreator;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntervalCreatorImpl implements ProducerIntervalCreator {

  @Override
  public ProducerAwardIntervalDomain createProducerAwardInterval(
      Entry<String, List<ProducerAwardIntervalDomain>> entry
  ) {
    var producer = entry.getKey();
    var intervals = entry.getValue();

    var minYearOpt = intervals.stream().mapToInt(ProducerAwardIntervalDomain::getPreviousWin).min();
    var maxYearOpt = intervals.stream().mapToInt(ProducerAwardIntervalDomain::getFollowingWin)
        .max();

    if (minYearOpt.isPresent() && maxYearOpt.isPresent()) {
      var minYear = minYearOpt.getAsInt();
      var maxYear = maxYearOpt.getAsInt();
      var interval = maxYear - minYear;

      if (interval > 0) {
        return ProducerAwardIntervalDomain.builder()
            .producer(producer)
            .interval(interval)
            .previousWin(minYear)
            .followingWin(maxYear)
            .build();
      }
    }
    return null;
  }
}
