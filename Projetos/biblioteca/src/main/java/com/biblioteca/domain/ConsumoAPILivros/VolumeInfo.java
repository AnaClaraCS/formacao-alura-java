package com.biblioteca.domain.ConsumoAPILivros;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
    public record VolumeInfo(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<String> autores,
        @JsonAlias("publisher") String editora,
        @JsonAlias("publishedDate") String dataPublicacao,
        @JsonAlias("description") String descricao,
        @JsonAlias("pageCount") int paginas,
        @JsonAlias("categories") List<String> categorias,
        @JsonAlias("imageLinks") ImageLinks imagem,
        @JsonAlias("industryIdentifiers") List<IndustryIdentifier> industryIdentifiers
    ) {}
