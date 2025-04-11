package com.api.domain.Medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.domain.Endereco.Especialidade;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long>{

    Page<DadosListagemMedicos> findAllByAtivoTrue(Pageable pagina);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.ativo = true
            AND m.especialidade = :especialidade
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.ativa = true
                AND c.data = :data)
            ORDER BY rand()
            LIMIT 1
            """)
    Medico escolherMedicoLivre(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo FROM Medico m
            WHERE m.id = :id
            """)
    boolean findAtivoById(Long id);
    
}
