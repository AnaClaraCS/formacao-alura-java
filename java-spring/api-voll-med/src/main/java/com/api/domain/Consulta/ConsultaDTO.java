package com.api.domain.Consulta;

import java.time.LocalDateTime;

import com.api.domain.Endereco.Especialidade;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ConsultaDTO(

    Long id,

    Long idMedico,

    @NotNull
    Long idPaciente,

    Especialidade especialidade,

    @NotNull
    @Future
    LocalDateTime data
) {
    
}
