package com.outsera_test.worst_movie_api.commom.util.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.outsera_test.worst_movie_api.commom.exceptions.CsvParsingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_CSV_PARSE_LINE_ERROR;
import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_CSV_PARSING_EXCEPTION;

@Slf4j
public class GenericCsvParser {


  public static <E, T> Set<T> parse(
      File file,
      Class<E> csvRepresentationType,
      Function<E, T> mapper,
      char separator
  ) {
    CsvValidator.validateCsvFile(file);

    try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath())) {
      HeaderColumnNameMappingStrategy<E> strategy = new HeaderColumnNameMappingStrategy<>();
      strategy.setType(csvRepresentationType);

      CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(bufferedReader)
          .withSkipLines(0)
          .withSeparator(separator)
          .withMappingStrategy(strategy)
          .withIgnoreEmptyLine(true)
          .withThrowExceptions(false)
          .withExceptionHandler(ignoreWrongLines())
          .withIgnoreLeadingWhiteSpace(true)
          .withErrorLocale(new Locale("pt", "BR"))
          .build();
      return csvToBean.parse()
          .stream()
          .map(mapper)
          .collect(Collectors.toSet());
    } catch (RuntimeException | IOException e) {
      throw new CsvParsingException(MESSAGE_CSV_PARSING_EXCEPTION, e);
    }
  }

  private static CsvExceptionHandler ignoreWrongLines() {
    return e -> {
      log.error(MESSAGE_CSV_PARSE_LINE_ERROR, e.getLineNumber(), e.getMessage());
      return null;
    };
  }
}
