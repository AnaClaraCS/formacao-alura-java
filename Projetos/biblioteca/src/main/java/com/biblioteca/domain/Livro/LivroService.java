package com.biblioteca.domain.Livro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.domain.Autor.Autor;
import com.biblioteca.domain.Serie.Serie;
import com.biblioteca.domain.repository.LivroRepository;

import jakarta.transaction.Transactional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repositorio;

    //CRUD

    public Livro salvar(Livro livro) {
        repositorio.save(livro);
        return livro;
    }
    
    public Livro atualizar(Livro livro) {
        return repositorio.save(livro);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    //BUSCAS

    public Livro buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }


    @Transactional
    public List<Livro> listarTodos() {
        return repositorio.findAll();
    }

    @Transactional
    public List<Livro> buscarPorTitulo(String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Livro> buscarPorEditora(String editora) {
        return repositorio.findByEditoraContainingIgnoreCase(editora);
    }

    // RELAÇÃO - Autor

    public void adicionarAutores(Livro livro, List<Autor> autores) {
        for (Autor autor : autores) {
            this.adicionarAutor(livro, autor);
        }
        salvar(livro);
    }

    public void adicionarAutor(Livro livro, Autor autor) {
        livro.adicionarAutor(autor);
        autor.adicionarLivro(livro);
        salvar(livro);
    }

    public void removerAutor(Livro livro, Autor autor){
        livro.removerAutor(autor);
        salvar(livro);
    }

    // RELAÇÃO - Serie

    public void adicionarSerie(Livro livro, Serie serie, double ordemSerie) {
        livro.definirSerie(serie, ordemSerie);
        salvar(livro);
    }

    public void removerSerie(Livro livro) {
        livro.removerSerie();
        salvar(livro);
    }

    
}
