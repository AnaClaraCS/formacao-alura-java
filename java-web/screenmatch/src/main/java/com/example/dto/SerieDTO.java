package com.example.dto;

import com.example.screenmatch.model.Categoria;



public record SerieDTO(
    Long id,
    String titulo,
    Integer totalTemporadas,
    double avaliacao,
    Categoria genero,
    String sinopse,
    String poster,
    String atores
) {
    
}
