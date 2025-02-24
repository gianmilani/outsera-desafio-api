package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerGrouper;
import com.outsera_test.worst_movie_api.core.service.producerMapper.ProducerIntervalCreator;
import com.outsera_test.worst_movie_api.core.service.producerMapper.WeightedAverageCalculator;
import com.outsera_test.worst_movie_api.core.service.producerMapper.impl.ResponseProducersAwardsBuilder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProducersAwardsMapperServiceImpl implements ProducersAwardsMapperService {

  private final ProducerGrouper producerGrouper;
  private final ProducerIntervalCreator producerIntervalCreator;
  private final WeightedAverageCalculator weightedAverageCalculator;
  private final ResponseProducersAwardsBuilder responseProducersAwardsBuilder;

  public ProducersAwardsMapperServiceImpl(ProducerGrouper producerGrouper,
      ProducerIntervalCreator producerIntervalCreator,
      WeightedAverageCalculator weightedAverageCalculator,
      ResponseProducersAwardsBuilder responseProducersAwardsBuilder) {
    this.producerGrouper = producerGrouper;
    this.producerIntervalCreator = producerIntervalCreator;
    this.weightedAverageCalculator = weightedAverageCalculator;
    this.responseProducersAwardsBuilder = responseProducersAwardsBuilder;
  }


  @Override
  public ResponseProducersAwards mapToResponseProducersAwards(
      List<ProducerAwardIntervalDomain> producersResults) {
    // Agrupar os resultados por produtor
    var groupedByProducer = producerGrouper.groupByProducer(producersResults);

    // Criar intervalos de prêmios do produtor e filtrar nulos
    var producerIntervals = groupedByProducer.entrySet().stream()
        .map(producerIntervalCreator::createProducerAwardInterval)
        .filter(Objects::nonNull)
        .toList();

    // Encontrar os intervalos máximo e mínimo
    var collect = producerIntervals.stream().collect(
        Collectors.teeing(
            Collectors.maxBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getInterval)),
            Collectors.minBy(Comparator.comparingInt(ProducerAwardIntervalDomain::getInterval)),
            (max, min) -> {
              Map<String, ProducerAwardIntervalDomain> map = new HashMap<>();
              map.put("max", max.orElse(null));
              map.put("min", min.orElse(null));
              return map;
            }
        )
    );

    // Construir e retornar a resposta
    return responseProducersAwardsBuilder.buildResponse(collect);
  }
}