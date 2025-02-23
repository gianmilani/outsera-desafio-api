package com.outsera_test.worst_movie_api.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerAwardIntervalDomain {
    private String producer;
    private Integer Interval;
    private Integer min;
    private Integer max;
}
