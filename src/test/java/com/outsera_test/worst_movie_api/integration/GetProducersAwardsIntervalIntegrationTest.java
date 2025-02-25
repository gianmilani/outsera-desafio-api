package com.outsera_test.worst_movie_api.integration;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.outsera_test.worst_movie_api.IntegrationTest;
import com.outsera_test.worst_movie_api.core.service.impl.CsvFileServiceImpl;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.MovieRepository;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

public class GetProducersAwardsIntervalIntegrationTest extends IntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MovieRepository repository;

  @Autowired
  private CsvFileServiceImpl csvFileService;

  private String PATH = "/api/v1/producers/awards";

  @BeforeEach
  void setUp() {
    repository.deleteAll();
  }

  @Test
  void shouldBeReturnProducersAwardsInterval() throws Exception {
    generateData();
    mockMvc.perform(get(PATH))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.min", hasSize(1)))
        .andExpect(jsonPath("$.max", hasSize(1)));
  }

  @Test
  void shouldReturnNotFoundWhenThereAreNoProducers() throws Exception {
    mockMvc.perform(get(PATH))
        .andExpect(status().isNotFound());
  }

  private void generateData() throws IOException {
    ClassPathResource resource = new ClassPathResource("movielist_original.csv");
    csvFileService.parseAndPersistData(resource.getFile());
  }
}
