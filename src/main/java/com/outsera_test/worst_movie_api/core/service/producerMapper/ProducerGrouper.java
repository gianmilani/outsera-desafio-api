package com.outsera_test.worst_movie_api.core.service.producerMapper;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import java.util.List;
import java.util.Map;

public interface ProducerGrouper {

  Map<String, List<ProducerAwardIntervalDomain>> groupByProducer(
      List<ProducerAwardIntervalDomain> producers);
}
