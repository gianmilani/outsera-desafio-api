package com.outsera_test.worst_movie_api.commom.exceptions;

public class CsvEmptyFileException extends RuntimeException {

  public CsvEmptyFileException(String message) {
    super(message);
  }
}