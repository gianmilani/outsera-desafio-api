package com.outsera_test.worst_movie_api.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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
    private int year;;
}
