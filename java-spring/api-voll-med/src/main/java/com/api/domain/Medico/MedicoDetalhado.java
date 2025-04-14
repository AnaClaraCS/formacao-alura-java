package com.api.domain.Medico;

import com.api.domain.Endereco.Endereco;

public record MedicoDetalhado(
    Long id,
    String nome,
    String email,
    String telefone,
    String crm,
    Especialidade especialidade,
    Endereco endereco
) {

    public MedicoDetalhado(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }

    public MedicoDetalhado(MedicoDTO medicoDTO){
        this(null, medicoDTO.nome(), medicoDTO.email(), medicoDTO.telefone(), medicoDTO.crm(), medicoDTO.especialidade(), new Endereco(medicoDTO.endereco()));
    }

}
