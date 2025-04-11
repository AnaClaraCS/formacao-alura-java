package com.api.domain.Medico;

import com.api.domain.Endereco.EnderecoDTO;
import com.api.domain.Endereco.Especialidade;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(
    @NotBlank
    Long id,

    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email,

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,

    @NotNull
    Especialidade especialidade,

    @NotNull
    @Valid
    EnderecoDTO endereco
) {
    
}
