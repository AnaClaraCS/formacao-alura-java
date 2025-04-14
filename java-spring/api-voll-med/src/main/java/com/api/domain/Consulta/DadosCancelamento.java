package com.api.domain.Consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
    @NotNull
    Long idConsulta,

    @NotNull
    MotivoCancelamento motivo
) {
    
}
