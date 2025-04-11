package com.api.domain.Consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndData(Long idPaciente, LocalDateTime data);
    Boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);
    
}
