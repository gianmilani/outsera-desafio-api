package com.outsera_test.worst_movie_api.commom.util.csv.strategies;


import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_FILE_NOT_EXIST_EXCEPTION;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import java.io.File;
import org.springframework.stereotype.Component;

@Component
public class FileExistenceStrategy implements ValidationStrategy {

  @Override
  public void validate(File file) {
    if (!file.isFile()) {
      throw new CsvInvalidFileException(MESSAGE_FILE_NOT_EXIST_EXCEPTION);
    }
  }
}
