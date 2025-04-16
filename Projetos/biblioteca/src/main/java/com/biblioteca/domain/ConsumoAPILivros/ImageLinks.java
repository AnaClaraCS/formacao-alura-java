package com.biblioteca.domain.ConsumoAPILivros;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ImageLinks(
    @JsonAlias("thumbnail") String thumbnail
) {}
