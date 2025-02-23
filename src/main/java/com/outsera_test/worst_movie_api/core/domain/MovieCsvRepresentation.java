package com.outsera_test.worst_movie_api.core.domain;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.validators.MustMatchRegexExpression;
import com.opencsv.bean.validators.PreAssignmentValidator;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCsvRepresentation {

  @PreAssignmentValidator(validator = MustMatchRegexExpression.class, paramString = "^[0-9]{4}$")
  @CsvBindByPosition(position = 0)
  private int year;
  @CsvBindByPosition(position = 1)
  private String title;
  @CsvBindByPosition(position = 2)
  private String studios;
  @CsvBindByPosition(position = 3)
  private String producers;
  @CsvBindByPosition(position = 4)
  private String winner;
}

