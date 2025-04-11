package com.api.domain.Paciente;

import com.api.domain.Endereco.Endereco;

public record PacienteDetalhado(
    Long id,
    String nome,
    String email,
    String cpf,
    String telefone,
    Endereco endereco
) {
    public PacienteDetalhado(Paciente p){
        this(p.getId(), p.getNome(), p.getEmail(), p.getCpf(), p.getTelefone(), p.getEndereco());
    }
}
