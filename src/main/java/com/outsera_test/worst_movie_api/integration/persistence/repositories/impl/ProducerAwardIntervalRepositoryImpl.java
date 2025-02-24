package com.outsera_test.worst_movie_api.integration.persistence.repositories.impl;

import com.outsera_test.worst_movie_api.commom.exceptions.ProducerAwardIntervalNotFoundException;
import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.ProducerAwardIntervalRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProducerAwardIntervalRepositoryImpl implements ProducerAwardIntervalRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<ProducerAwardIntervalDomain> getProducerWithLongestAndFastestAwardsInterval() {
    String query = """
         SELECT
            m.producers as producer,
            MAX(m.year) - MIN(m.year) AS interval,
            MIN(m.year) AS min,
            MAX(m.year) AS max
         FROM
            MovieEntity m
         WHERE
            m.winner = true
         GROUP BY
            m.producers
         ORDER BY
            MIN(m.year) ASC
        """;

    TypedQuery<ProducerAwardIntervalDomain> typedQuery = em.createQuery(query,
        ProducerAwardIntervalDomain.class);

    if (typedQuery.getResultList().isEmpty()) {
      throw new ProducerAwardIntervalNotFoundException();
    }

    return typedQuery.getResultList();
  }
}