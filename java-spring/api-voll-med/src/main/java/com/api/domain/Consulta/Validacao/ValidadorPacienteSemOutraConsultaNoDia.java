package com.api.domain.Consulta.Validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ConsultaRepository;
import com.api.domain.Consulta.ValidacaoException;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(ConsultaDTO dados) {
        if(consultaRepository.existsByPacienteIdAndData(dados.idPaciente(), dados.data()) ){
            throw new ValidacaoException( "Paciente já possui uma consulta agendada para o mesmo dia.");
        }
    }

}
