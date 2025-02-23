package com.outsera_test.worst_movie_api.commom.exceptions;

public class CsvPersistenceException extends RuntimeException {
    public CsvPersistenceException(String message) {
        super(message);
    }

    public CsvPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}