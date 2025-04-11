package com.api.domain.Consulta;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Consulta.Validacao.ValidadorConsulta;
import com.api.domain.Medico.Medico;
import com.api.domain.Medico.MedicoRepository;
import com.api.domain.Paciente.Paciente;
import com.api.domain.Paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired 
    private MedicoRepository medicoRepository;

    @Autowired
    private List<ValidadorConsulta> validadores;

    public void agendarConsulta(ConsultaDTO dados) {

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException ("Paciente não encontrado");
        }
        if( dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException ("Médico não encontrado");
        }

        validadores.forEach(v -> v.validar(dados));

        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        Medico medico = escolherMedico(dados);

        Consulta consulta = new Consulta(null, paciente, medico, dados.especialidade(), dados.data());
        consultaRepository.save(consulta);

    }

    private Medico escolherMedico(ConsultaDTO dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }else if(dados.especialidade() != null){
            throw new ValidacaoException("É obrigatório informar especialidade caso não informe o médico");
        }else{
            return medicoRepository.escolherMedicoLivre(dados.especialidade(), dados.data());
        }
    }

    public void cancelar(Long id, String motivo) {
        if(!consultaRepository.existsById(id)){
            throw new ValidacaoException("Consulta não encontrada");
        }
        Consulta consulta = consultaRepository.getReferenceById(id);
        if(!consulta.getData().isBefore(LocalDateTime.now().plusHours(24))){
            throw new ValidacaoException("Não é possível cancelar uma consulta com menos de 24h de antecedência");
        }
        consulta.cancelar(MotivoCancelamento.fromString(motivo));
    }
    
}
