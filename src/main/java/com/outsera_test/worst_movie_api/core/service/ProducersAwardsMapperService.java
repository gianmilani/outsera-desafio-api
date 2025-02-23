package com.outsera_test.worst_movie_api.core.service;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import java.util.List;

public interface ProducersAwardsMapperService {

  ResponseProducersAwards mapToResponseProducersAwards(List<ProducerAwardIntervalDomain> producers);
}
