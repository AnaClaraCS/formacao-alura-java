package com.api.domain.Paciente;

import com.api.domain.Endereco.EnderecoDTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente (
    @NotNull
    Long id,
    String nome,
    String telefone,
    EnderecoDTO endereco
){
    
}
