package com.outsera_test.worst_movie_api.integration.persistence.repositories;

import com.outsera_test.worst_movie_api.integration.persistence.entities.MovieEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

  Optional<MovieEntity> findByTitleAndProducersAndStudiosAndYear(
      String title,
      String producers,
      String studios,
      Integer year
  );
}
