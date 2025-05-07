package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {

    boolean existsTutorAndStatus(Tutor tutor, StatusAdocao aguardandoAvaliacao);

    boolean existsPetAndStatus(Pet pet, StatusAdocao aguardandoAvaliacao);

    List<Adocao> findByTutorAndStatus(Tutor tutor, StatusAdocao aprovado);

}
