package com.api.domain.Consulta.Validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ValidacaoException;
import com.api.domain.Paciente.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorConsulta{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(ConsultaDTO dados) {
        if(!pacienteRepository.findAtivoById(dados.idPaciente())){
            throw new ValidacaoException("Paciente não está ativo");
        }
    }
    
}
