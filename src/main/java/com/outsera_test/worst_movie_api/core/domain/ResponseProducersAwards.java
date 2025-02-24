package com.outsera_test.worst_movie_api.core.domain;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProducersAwards {

  private List<ProducerAwardIntervalDomain> min;
  private List<ProducerAwardIntervalDomain> max;
}
