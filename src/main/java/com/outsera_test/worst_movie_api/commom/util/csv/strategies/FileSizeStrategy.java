package com.outsera_test.worst_movie_api.commom.util.csv.strategies;

import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_FILE_SIZE_EXCEPTION;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class FileSizeStrategy implements ValidationStrategy {

  private static final long MAX_FILE_SIZE = 3 * 1024 * 1024;

  @Override
  public void validate(File file) {
    if (file.length() > MAX_FILE_SIZE) {
      throw new CsvInvalidFileException(MESSAGE_FILE_SIZE_EXCEPTION);
    }
  }
}
