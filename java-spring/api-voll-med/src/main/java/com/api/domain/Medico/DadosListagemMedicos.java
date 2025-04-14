package com.api.domain.Medico;

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
