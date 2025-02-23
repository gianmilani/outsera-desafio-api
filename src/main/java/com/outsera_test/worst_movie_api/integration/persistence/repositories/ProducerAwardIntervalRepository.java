package com.outsera_test.worst_movie_api.integration.persistence.repositories;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import java.util.List;


public interface ProducerAwardIntervalRepository {
    List<ProducerAwardIntervalDomain> getProducerWithLongestAndFastestAwardsInterval();
}
