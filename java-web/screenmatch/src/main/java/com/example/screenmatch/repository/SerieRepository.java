package com.example.screenmatch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.screenmatch.model.Categoria;
import com.example.screenmatch.model.Episodio;
import com.example.screenmatch.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

    Serie findByTituloContainsIgnoreCase(String nomeSerie);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND e.dataLancamento >= :data")
    List<Episodio> episodiosAposData(Serie serie, LocalDate data);

    List<Serie> findAllByAtoresContainsIgnoreCase(String nomeAtor);

    List<Serie> findAllByAvaliacaoGreaterThanAndTotalTemporadasLessThan(double avaliacaoMinima, int temporadasMaximas);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND e.titulo ILIKE %:trecho%")
    List<Episodio> episodioPorTrecho(Serie serie, String trecho);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long numero);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id")
    List<Episodio> obterTodosEpisodios(Long id);

    List<Serie> findAllByGenero(Categoria categoria);
    

}
