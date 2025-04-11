package com.api.domain.Paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

    Page<DadosListagemPacientes> findAllByAtivoTrue(Pageable pagina);

    @Query("""
            SELECT p.ativo FROM Paciente p
            WHERE p.id = :id
            """)
    boolean findAtivoById(Long idPaciente);
    
}
