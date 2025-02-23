package com.outsera_test.worst_movie_api.integration;

import com.outsera_test.worst_movie_api.IntegrationTest;
import com.outsera_test.worst_movie_api.core.service.impl.CsvFileServiceImpl;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.MovieRepository;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

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
                .andExpect(jsonPath("$.min", hasSize(5)))
                .andExpect(jsonPath("$.max", hasSize(2)))
                .andExpect(jsonPath("$.min[0].producer", is("Alan Smithee")))
                .andExpect(jsonPath("$.min[0].min", is(1980)))
                .andExpect(jsonPath("$.min[0].max", is(1981)))
                .andExpect(jsonPath("$.min[0].interval", is(1)))
                .andExpect(jsonPath("$.min[4].producer", is("Gloria Katz")))
                .andExpect(jsonPath("$.min[4].min", is(1986)))
                .andExpect(jsonPath("$.min[4].max", is(1986)))
                .andExpect(jsonPath("$.min[4].interval", is(0)))
                .andExpect(jsonPath("$.max[0].producer", is("Bo Derek")))
                .andExpect(jsonPath("$.max[0].min", is(1984)))
                .andExpect(jsonPath("$.max[0].max", is(1990)))
                .andExpect(jsonPath("$.max[0].interval", is(6)))
                .andExpect(jsonPath("$.max[1].producer", is("Buzz Feitshans")))
                .andExpect(jsonPath("$.max[1].min", is(1985)))
                .andExpect(jsonPath("$.max[1].max", is(1994)))
                .andExpect(jsonPath("$.max[1].interval", is(9)));
    }

    @Test
    void shouldReturnNotFoundWhenThereAreNoProducers() throws Exception {
        mockMvc.perform(get(PATH))
                .andExpect(status().isNotFound());
    }

    private void generateData() throws IOException {
        ClassPathResource resource = new ClassPathResource("movielist_search.csv");
        csvFileService.parseAndPersistData(resource.getFile());
    }
}
