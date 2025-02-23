package com.outsera_test.worst_movie_api.core.service.impl;

import com.outsera_test.worst_movie_api.core.domain.ProducerAwardIntervalDomain;
import com.outsera_test.worst_movie_api.core.domain.ResponseProducersAwards;
import com.outsera_test.worst_movie_api.core.service.ProducersAwardsMapperService;
import java.util.List;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.partitioningBy;

@Service
public class ProducersAwardsMapperServiceImpl implements ProducersAwardsMapperService {
    @Override
    public ResponseProducersAwards mapToResponseProducersAwards(List<ProducerAwardIntervalDomain> producers) {
        var weightedAverage = calculateWeightedAverage(producers);
        var partitionedProducers = producers.stream().collect(
                partitioningBy(producer -> producer.getInterval() < weightedAverage)
        );

        var minIntervalProducers = partitionedProducers.get(true);
        var maxIntervalProducers = partitionedProducers.get(false);

        return ResponseProducersAwards.builder()
                .min(minIntervalProducers)
                .max(maxIntervalProducers)
                .build();
    }

    private double calculateWeightedAverage(List<ProducerAwardIntervalDomain> producers) {
        var totalWeightedInterval = producers.stream()
                .mapToDouble(producer -> producer.getInterval() * producer.getMax())
                .sum();
        var totalMaxYears = producers.stream()
                .mapToInt(ProducerAwardIntervalDomain::getMax)
                .sum();
        return totalWeightedInterval / totalMaxYears;
    }
}
