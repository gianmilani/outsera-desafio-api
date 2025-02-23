package com.outsera_test.worst_movie_api.commom.util.csv.strategies;

import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_FILE_EMPTY_EXCEPTION;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.stereotype.Component;

@Component
public class FileEmptyStrategy implements ValidationStrategy {

  @Override
  public void validate(File file) {
    try {
      if (file.length() == 0 || Files.readAllLines(file.toPath()).isEmpty()) {
        throw new CsvInvalidFileException(MESSAGE_FILE_EMPTY_EXCEPTION);
      }
    } catch (IOException e) {
      throw new CsvInvalidFileException(MESSAGE_FILE_EMPTY_EXCEPTION, e);
    }
  }
}
