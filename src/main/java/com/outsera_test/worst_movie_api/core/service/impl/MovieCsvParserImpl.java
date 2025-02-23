package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import com.outsera_test.worst_movie_api.commom.exceptions.CsvParsingException;
import com.outsera_test.worst_movie_api.commom.util.csv.GenericCsvParser;
import com.outsera_test.worst_movie_api.core.domain.MovieCsvRepresentation;
import com.outsera_test.worst_movie_api.core.domain.MovieDomain;
import com.outsera_test.worst_movie_api.core.service.MovieCsvParser;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_CSV_PARSING_EXCEPTION;

@Slf4j
@Component
public class MovieCsvParserImpl implements MovieCsvParser {
    private final String WINNER_CHECK = "yes";

    @Override
    public Set<MovieDomain> parse(File file) {
        try {
            return GenericCsvParser.parse(
                    file,
                    MovieCsvRepresentation.class,
                    csvLine -> MovieDomain.builder()
                            .title(csvLine.getTitle())
                            .studios(csvLine.getStudios())
                            .producers(csvLine.getProducers())
                            .winner(isMovieWinner(csvLine.getWinner()))
                            .year(csvLine.getYear())
                            .build(),
                    ';'
            );
        } catch (CsvParsingException | CsvInvalidFileException e) {
            log.error(MESSAGE_CSV_PARSING_EXCEPTION, e.getMessage());
            return Collections.emptySet();
        }
    }

    private boolean isMovieWinner(String winner) {
        return WINNER_CHECK.equalsIgnoreCase(winner);
    }
}
