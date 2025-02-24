package com.outsera_test.worst_movie_api.core.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProducersAwards {

  private ProducerAwardIntervalDomain min;
  private ProducerAwardIntervalDomain max;
}
