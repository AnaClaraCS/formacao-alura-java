package com.api.domain.Consulta;

import java.time.LocalDateTime;

import com.api.domain.Endereco.Especialidade;
import com.api.domain.Medico.Medico;
import com.api.domain.Paciente.Paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Consulta")
@Table(name = "consultas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    private Especialidade especialidade;

    private LocalDateTime data;

    private Boolean ativa;

    private MotivoCancelamento motivoCancelamento;

    public Consulta(Long id, Paciente paciente, Medico medico, Especialidade especialidade, LocalDateTime data) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.especialidade = especialidade;
        this.data = data;
        this.ativa = true;
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.ativa = false;
        this.motivoCancelamento = motivo;
    }
    

    
}
