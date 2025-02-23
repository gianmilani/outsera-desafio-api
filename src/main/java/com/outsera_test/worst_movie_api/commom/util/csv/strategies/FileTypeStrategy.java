package com.outsera_test.worst_movie_api.commom.util.csv.strategies;

import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_FILE_TYPE_EXCEPTION;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class FileTypeStrategy implements ValidationStrategy {

  private static final String CONTENT_TYPE_VALID = ".csv";

  @Override
  public void validate(File file) {
    if (!file.getName().toLowerCase().endsWith(CONTENT_TYPE_VALID)) {
      throw new CsvInvalidFileException(String.format(MESSAGE_FILE_TYPE_EXCEPTION, file.getName()));
    }
  }
}
