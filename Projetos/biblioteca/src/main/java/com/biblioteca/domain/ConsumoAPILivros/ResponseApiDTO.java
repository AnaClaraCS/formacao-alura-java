package com.biblioteca.domain.ConsumoAPILivros;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseApiDTO(
    @JsonAlias("items") List<LivroResponseDTO> livros
) {}