package com.biblioteca.Serie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.repository.SerieRepository;

import jakarta.transaction.Transactional;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    public Serie salvar(Serie serie) {
        repositorio.save(serie);
        return serie;
    }

    public Serie buscarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        repositorio.deleteById(id);
    }

    public Serie atualizar(Serie serie) {
        return repositorio.save(serie);
    }

    @Transactional
    public List<Serie> listarTodos() {
        return repositorio.findAllComLivros();
    }

    public List<Serie> buscarPorTitulo(String titulo) {
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    
}