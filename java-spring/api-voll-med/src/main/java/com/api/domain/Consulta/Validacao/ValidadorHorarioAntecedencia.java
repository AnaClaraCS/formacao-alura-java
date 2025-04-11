package com.api.domain.Consulta.Validacao;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ValidacaoException;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorConsulta {

    @Override
    public void validar(ConsultaDTO dados) {
        var agora = LocalDateTime.now();
        var antesDeDuasHoras = dados.data().isBefore(agora.plusMinutes(30));
        if (antesDeDuasHoras) {
            throw new ValidacaoException("Consulta deve ser agendada com pelo menos 30 minutos de antecedência");
        }
    }
    
}
