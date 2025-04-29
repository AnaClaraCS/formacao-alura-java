package com.biblioteca.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.biblioteca.domain.Livro.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

   // List<Livro> findByAutoresContainingIgnoreCase(String autor);

    List<Livro> findByEditoraContainingIgnoreCase(String editora);

   // List<Livro> findBySerie(Serie serie);

}
