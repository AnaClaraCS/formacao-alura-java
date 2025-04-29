package com.biblioteca.domain.Livro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repositorio;

    public Livro salvar(Livro livro) {
        repositorio.save(livro);
        return livro;
    }

    public Livro buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    public Livro atualizar(Livro livro) {
        return repositorio.save(livro);
    }

    public List<Livro> listarTodos() {
        return repositorio.findAll();
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }
    
    public List<Livro> buscarPorAutor(String autor) {
        return repositorio.findByAutoresContainingIgnoreCase(autor);
    }

    public List<Livro> buscarPorEditora(String editora) {
        return repositorio.findByEditoraContainingIgnoreCase(editora);
    }
    
}
