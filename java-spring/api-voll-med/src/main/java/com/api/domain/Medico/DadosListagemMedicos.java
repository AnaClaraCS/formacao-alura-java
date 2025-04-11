package com.api.domain.Medico;

import com.api.domain.Endereco.Especialidade;

public record DadosListagemMedicos(
    Long id,
    String nome, 
    String email, 
    String crm, 
    Especialidade especialidade
) {

    public DadosListagemMedicos(Medico m){
        this(m.getId(), m.getNome(), m.getEmail(), m.getCrm(), m.getEspecialidade());
    }
}
