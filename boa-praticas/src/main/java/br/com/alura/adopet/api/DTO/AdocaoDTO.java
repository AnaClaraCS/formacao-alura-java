package br.com.alura.adopet.api.DTO;

public record AdocaoDTO(
    Long id,
    Long idPet,
    Long idTutor,
    String justificativa
) {
    
}
