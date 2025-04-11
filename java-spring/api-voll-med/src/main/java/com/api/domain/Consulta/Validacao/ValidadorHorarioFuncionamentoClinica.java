package com.api.domain.Consulta.Validacao;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import com.api.domain.Consulta.ConsultaDTO;
import com.api.domain.Consulta.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorConsulta {

    @Override
    public void validar(ConsultaDTO dados) {
        var domingo = dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dados.data().getHour() < 7;
        var depoisFechamento = dados.data().getHour() > 18;
        if (domingo || antesAbertura || depoisFechamento) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
    
}
