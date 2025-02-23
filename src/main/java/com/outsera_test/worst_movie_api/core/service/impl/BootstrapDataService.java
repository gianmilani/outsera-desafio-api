package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.service.CsvFileService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import static org.apache.logging.log4j.util.LoaderUtil.getClassLoader;

@Slf4j
@Component
public class BootstrapDataService implements CommandLineRunner {
    private static final String MOVIE_LIST_FILE = "data/movielist.csv";
    private final CsvFileService csvFileService;

    public BootstrapDataService(CsvFileService csvFileService) {
        this.csvFileService = csvFileService;
    }

    @Override
    public void run(String... args) {
        Path filePath = Paths.get(Objects.requireNonNull(getClassLoader().getResource(MOVIE_LIST_FILE)).getPath());
        csvFileService.parseAndPersistData(filePath.toFile());
    }
}
