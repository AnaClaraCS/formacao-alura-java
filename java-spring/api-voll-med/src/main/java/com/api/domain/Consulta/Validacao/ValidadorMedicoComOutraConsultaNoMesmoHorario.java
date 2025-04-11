package com.api.domain.Consulta.Validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ConsultaRepository;
import com.api.domain.Consulta.ValidacaoException;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaDTO dados) {
        if(consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data())){
            throw new ValidacaoException("Médico já possui outra consulta nesse horário");
        }
    }
    
}
