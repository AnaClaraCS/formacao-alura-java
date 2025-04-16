package com.biblioteca.domain.ConsumoAPILivros;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IndustryIdentifier(
    @JsonAlias("type") String tipo,
    @JsonAlias("identifier") String identificador
) {}