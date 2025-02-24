package com.outsera_test.worst_movie_api.commom.exceptions;

import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.MESSAGE_PRODUCERS_AWARDS_NOT_FOUND_EXCEPTION;

import org.springframework.http.HttpStatus;

public class ProducerAwardIntervalNotFoundException extends CustomException {

  @Override
  HttpStatus getStatusCode() {
    return HttpStatus.NOT_FOUND;
  }

  @Override
  String getCustomerMessage() {
    return MESSAGE_PRODUCERS_AWARDS_NOT_FOUND_EXCEPTION;
  }
}