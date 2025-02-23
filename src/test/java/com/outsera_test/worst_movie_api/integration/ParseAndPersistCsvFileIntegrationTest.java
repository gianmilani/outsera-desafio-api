package com.outsera_test.worst_movie_api.integration;

import com.outsera_test.worst_movie_api.IntegrationTest;
import com.outsera_test.worst_movie_api.core.domain.mapper.MovieDomainMapper;
import com.outsera_test.worst_movie_api.core.service.MovieCsvParser;
import com.outsera_test.worst_movie_api.core.service.impl.CsvFileServiceImpl;
import com.outsera_test.worst_movie_api.integration.persistence.entities.MovieEntity;
import com.outsera_test.worst_movie_api.integration.persistence.repositories.MovieRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

public class ParseAndPersistCsvFileIntegrationTest extends IntegrationTest {

  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieDomainMapper movieDomainMapper;

  @Autowired
  private MovieCsvParser movieCsvParser;

  @Autowired
  private CsvFileServiceImpl csvFileService;


  @Test
  @Sql(scripts = "/cleanmovies.sql")
  void shouldBeParseAndPersistWhenCsvFileIsCorrect() throws IOException {
    csvFileService.parseAndPersistData(getFile("movielist.csv"));

    List<MovieEntity> movies = movieRepository.findAll();
    assertThat(movies).isNotEmpty();
    assertThat(movies).hasSize(223);
  }

  @Test
  @Sql(scripts = "/cleanmovies.sql")
  void shouldNotParseAndPersistWhenCsvFileIsInvalidType() throws IOException {
    csvFileService.parseAndPersistData(getFile("movielist.xml"));

    List<MovieEntity> movies = movieRepository.findAll();
    assertThat(movies).isEmpty();
  }

  @Test
  @Sql(scripts = "/cleanmovies.sql")
  void shouldNotParseAndPersistWhenCsvFileIsCorupted() throws IOException {
    csvFileService.parseAndPersistData(getFile("movielist_corupted.csv"));

    List<MovieEntity> movies = movieRepository.findAll();
    assertThat(movies).isEmpty();
  }

  @Test
  @Sql(scripts = "/cleanmovies.sql")
  void shouldNotParseAndPersistWhenCsvFileIsEmpty() throws IOException {
    csvFileService.parseAndPersistData(getFile("movielist_empty.csv"));

    List<MovieEntity> movies = movieRepository.findAll();
    assertThat(movies).isEmpty();
  }

  @Test
  @Sql(scripts = "/cleanmovies.sql")
  void shouldBeParseAndPersistWhenCsvFileJustTwoData() throws IOException {
    csvFileService.parseAndPersistData(getFile("movielist.csv"));

    List<MovieEntity> movies = movieRepository.findAll();
    assertThat(movies).isNotEmpty();
    assertThat(movies).hasSize(223);
  }

  private File getFile(String fileName) throws IOException {
    ClassPathResource resource = new ClassPathResource(fileName);
    return resource.getFile();
  }
}
