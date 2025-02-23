package com.outsera_test.worst_movie_api.core.service;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvParsingException;
import com.outsera_test.worst_movie_api.core.domain.MovieDomain;
import java.io.File;
import java.util.Set;

public interface MovieCsvParser {

  Set<MovieDomain> parse(File file) throws CsvParsingException;
}
