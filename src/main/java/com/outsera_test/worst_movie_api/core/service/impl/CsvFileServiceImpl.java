package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import com.outsera_test.worst_movie_api.commom.exceptions.CsvParsingException;
import com.outsera_test.worst_movie_api.core.domain.mapper.MovieDomainMapper;
import com.outsera_test.worst_movie_api.core.service.CsvFileService;
import com.outsera_test.worst_movie_api.core.service.MovieCsvParser;
import com.outsera_test.worst_movie_api.integration.persistence.entities.MovieEntity;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.MovieRepository;
import java.io.File;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_CSV_PARSING_EXCEPTION;
import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_CSV_PERSIST_EXCEPTION;

@Slf4j
@Component
public class CsvFileServiceImpl implements CsvFileService {
    private final MovieRepository movieRepository;
    private final MovieDomainMapper movieDomainMapper;
    private final MovieCsvParser movieCsvParser;

    public CsvFileServiceImpl(
            MovieRepository movieRepository,
            MovieDomainMapper movieDomainMapper,
            MovieCsvParser movieCsvParser
    ) {
        this.movieRepository = movieRepository;
        this.movieDomainMapper = movieDomainMapper;
        this.movieCsvParser = movieCsvParser;
    }

    @Override
    public void parseAndPersistData(File file) {
        List<MovieEntity> moviesList = parseCsvFile(file);
        if (!moviesList.isEmpty()) idempotentPersistMovies(moviesList);
    }

    private List<MovieEntity> parseCsvFile(File file) {
        try {
            return movieCsvParser.parse(file).stream()
                    .map(movieDomainMapper::toEntity)
                    .toList();
        } catch (CsvParsingException | CsvInvalidFileException e) {
            log.error(MESSAGE_CSV_PARSING_EXCEPTION, e.getMessage());
            return Collections.emptyList();
        }
    }

    @Transactional
    private void idempotentPersistMovies(List<MovieEntity> moviesList) {
        try {
            moviesList.forEach(movie -> {
                movieRepository.findByTitleAndProducersAndStudiosAndYear(
                        movie.getTitle(),
                        movie.getProducers(),
                        movie.getStudios(),
                        movie.getYear()
                ).orElseGet(() -> movieRepository.save(movie));
            });
        } catch (RuntimeException e) {
            log.error(MESSAGE_CSV_PERSIST_EXCEPTION, e);
        }
    }
}
