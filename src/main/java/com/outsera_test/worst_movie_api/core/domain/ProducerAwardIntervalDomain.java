package com.outsera_test.worst_movie_api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerAwardIntervalDomain {
    private String producer;
    private Integer interval;
    private Integer min;
    private Integer max;
}
