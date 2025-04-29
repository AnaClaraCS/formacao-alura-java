package com.biblioteca.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.biblioteca.domain.Serie.Serie;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    List<Serie> findByTituloContainingIgnoreCase(String titulo);

    //List<Serie> findByAutoresContainingIgnoreCase(String autor);

    @Query("SELECT s FROM Serie s LEFT JOIN FETCH s.livros")
    List<Serie> findAllComLivros();

}
