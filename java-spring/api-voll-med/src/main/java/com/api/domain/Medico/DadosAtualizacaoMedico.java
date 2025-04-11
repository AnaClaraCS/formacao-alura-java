package com.api.domain.Medico;

import com.api.domain.Endereco.EnderecoDTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    EnderecoDTO endereco
) {


}
