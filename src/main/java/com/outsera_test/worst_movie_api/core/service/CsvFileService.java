package com.outsera_test.worst_movie_api.core.service;

import java.io.File;

public interface CsvFileService {

  void parseAndPersistData(File file);
}
