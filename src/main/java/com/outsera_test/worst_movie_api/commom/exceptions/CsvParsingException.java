package com.outsera_test.worst_movie_api.commom.exceptions;

public class CsvParsingException extends RuntimeException {

  public CsvParsingException(String message, Throwable cause) {
    super(message, cause);
  }
}
