package com.outsera_test.worst_movie_api.integration.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "MOVIES",
    indexes = {
        @Index(name = "PRODUCERS_IDX", columnList = "producers"),
        @Index(name = "TITLE_IDX", columnList = "title"),
        @Index(name = "YEAR_IDX", columnList = "year"),
    }
    , uniqueConstraints = @UniqueConstraint(columnNames = {"year", "title", "producers", "studios"})
)
public class MovieEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String studios;
  private String producers;
  private boolean winner;
  private Integer year;
}
