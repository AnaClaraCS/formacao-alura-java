package com.biblioteca.domain.ConsumoAPILivros;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroResponseDTO(
    @JsonAlias("selfLink") String link,
    @JsonAlias("volumeInfo") VolumeInfo volumeInfo

) {
    
}
