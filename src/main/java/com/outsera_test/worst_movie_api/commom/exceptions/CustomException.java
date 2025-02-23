package com.outsera_test.worst_movie_api.commom.exceptions;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {
    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    abstract HttpStatus getStatusCode();
    abstract String getCustomerMessage();
}
