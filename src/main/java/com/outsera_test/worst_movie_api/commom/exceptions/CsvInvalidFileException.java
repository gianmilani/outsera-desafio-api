package com.outsera_test.worst_movie_api.commom.exceptions;

public class CsvInvalidFileException extends RuntimeException {
    public CsvInvalidFileException(String message) {
        super(message);
    }

    public CsvInvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }
}