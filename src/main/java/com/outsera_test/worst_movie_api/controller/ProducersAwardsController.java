package com.outsera_test.worst_movie_api.controller;

import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.dto.ErrorResponseDto;
import com.outsera_test.worst_movie_api.core.service.MovieSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "REST API vencedores da categoria Pior Filme",
    description = """
        Retorna o(s) produtor(es) com maior intervalo entre dois prêmios consecutivos, e o que 
        "obteve dois prêmios mais rápido
        """
)
@RestController
@RequestMapping(path = "/api/v1/producers/awards", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProducersAwardsController {

  private final MovieSearchService movieSearchService;

  public ProducersAwardsController(MovieSearchService movieSearchService) {
    this.movieSearchService = movieSearchService;
  }

  @Operation(
      summary = "Obtem detalhes dos vencedores",
      description = "API REST busca os detalhes dos produtores da categoria Pior Filme"
  )
  @ApiResponses({
      @ApiResponse(
          responseCode = "200",
          description = "HTTP Status OK"
      ),
      @ApiResponse(
          responseCode = "404",
          description = "HTTP Status Not Found",
          content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          )
      )
  })
  @GetMapping
  public ResponseEntity<ResponseProducersAwards> getProducers() {
    ResponseProducersAwards producersAwards = movieSearchService.getProducersAwards();
    return ResponseEntity.ok(producersAwards);
  }
}
