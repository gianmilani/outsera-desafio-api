package com.outsera_test.worst_movie_api.commom.util;

public class ConstantsUtils {

    //MESSAGES CSV EXCEPTIONS AND LOGS
    public static final String MESSAGE_CSV_PARSING_EXCEPTION = "Erro ao processar o arquivo CSV: {}";
    public static final String MESSAGE_CSV_PERSIST_EXCEPTION = "Erro ao persistir filmes CSV";
    public static final String MESSAGE_CSV_PARSE_LINE_ERROR = "Erro de parse na linha: {} - Motivo: {}";
    //MESSAGES PRODUCERS AWARDS
    public static final String MESSAGE_PRODUCERS_AWARDS_NOT_FOUND_EXCEPTION = "Não foram encontrados produtores para essa pesquisa";


    //MESSAGES FILE CSV VALIDATION
    public static final String MESSAGE_FILE_EMPTY_EXCEPTION = "O arquivo está vazio.";
    public static final String MESSAGE_FILE_TYPE_EXCEPTION = "Tipo do arquivo inválido [%s]";
    public static final String MESSAGE_FILE_SIZE_EXCEPTION = "O arquivo excede o tamanho máximo permitido de 3MB.";

    private ConstantsUtils() {
        throw new AssertionError("Não é permitido instanciar a classe ConstantsUtils");
    }
}
