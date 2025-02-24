package com.outsera_test.worst_movie_api.core.service.producerMapper;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import java.util.List;
import java.util.Map.Entry;

public interface ProducerIntervalCreator {

  ProducerAwardIntervalDomain createProducerAwardInterval(
      Entry<String, List<ProducerAwardIntervalDomain>> entry);
}
