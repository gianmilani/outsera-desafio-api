package com.outsera_test.worst_movie_api.commom.util.csv;

import com.outsera_test.worst_movie_api.commom.exceptions.CsvInvalidFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.outsera_test.worst_movie_api.commom.util.ConstantsUtils.*;

public class CsvValidator {
    private static final Logger log = LoggerFactory.getLogger(CsvValidator.class);
    private static final long MAX_FILE_SIZE = 3 * 1024 * 1024;
    private static final String CONTENT_TYPE_VALID = ".csv";

    //TODO(Talvez, usar um strategy caso as regras aumentem)
    public static void validateCsvFile(File file) {
        String message = null;
        if (!file.isFile()) throw new CsvInvalidFileException("Arquivo não existe");
        try {
            if (file.length()==0 || Files.readAllLines(file.toPath()).isEmpty()) {
                message = MESSAGE_FILE_EMPTY_EXCEPTION;
            } else if (!file.getName().toLowerCase().endsWith(CONTENT_TYPE_VALID)) {
                message = String.format(MESSAGE_FILE_TYPE_EXCEPTION, file.getName());
            } else if (file.length() > MAX_FILE_SIZE) {
                message = MESSAGE_FILE_SIZE_EXCEPTION;
            }
        } catch (IOException | RuntimeException e) {
            throw new CsvInvalidFileException(message, e);
        }
        if (message!=null) throw new CsvInvalidFileException(message);
    }
}