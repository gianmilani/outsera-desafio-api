package com.outsera_test.worst_movie_api.commom.util.csv;

import com.outsera_test.worst_movie_api.commom.util.csv.strategies.ValidationStrategy;
import java.io.File;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvValidator {

  private final List<ValidationStrategy> validationStrategies;

  public CsvValidator(List<ValidationStrategy> validationStrategies) {
    this.validationStrategies = validationStrategies;
  }

  public void validateCsvFile(File file) {
    for (ValidationStrategy strategy : validationStrategies) {
      strategy.validate(file);
    }
  }
}