package com.outsera_test.worst_movie_api.core.domain.mapper;

import com.outsera_test.worst_movie_api.core.domain.MovieDomain;
import com.outsera_test.worst_movie_api.integration.persistence.entities.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieDomainMapper {

    MovieDomain toDomain(MovieEntity movieEntity);

    @Mapping(target = "id", ignore = true)
    MovieEntity toEntity(MovieDomain movieDomain);
}
