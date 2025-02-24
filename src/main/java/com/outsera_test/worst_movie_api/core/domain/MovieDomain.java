package com.outsera_test.worst_movie_api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDomain {

  private String title;
  private String studios;
  private String producers;
  private boolean winner;
  private int year;
  ;
}
