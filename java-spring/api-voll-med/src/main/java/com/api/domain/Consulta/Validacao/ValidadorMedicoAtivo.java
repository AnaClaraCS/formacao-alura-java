package com.api.domain.Consulta.Validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ValidacaoException;
import com.api.domain.Medico.MedicoRepository;

@Component
public class ValidadorMedicoAtivo implements ValidadorConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(ConsultaDTO dados) {

        if(dados.idMedico() == null){
            return;
        }

        if(!medicoRepository.findAtivoById(dados.idMedico())){
            throw new ValidacaoException("Médico não está ativo");
        }
    }
    
}
