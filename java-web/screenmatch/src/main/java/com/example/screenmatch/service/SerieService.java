package com.example.screenmatch.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.SerieDTO;
import com.example.screenmatch.model.Categoria;
import com.example.screenmatch.model.Episodio;
import com.example.screenmatch.model.EpisodioDTO;
import com.example.screenmatch.model.Serie;
import com.example.screenmatch.repository.SerieRepository;

@Service
public class SerieService {
    
    @Autowired
    private SerieRepository repositorio;
    
    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
        .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
        .collect(Collectors.toList());
    }
    
    private List<EpisodioDTO> converterEpisodios(List<Episodio> episodios) {
       return episodios.stream()
       .map(e -> new EpisodioDTO(e.getNumero(), e.getTemporada(), e.getTitulo()))
       .collect(Collectors.toList());
    }

    public List<SerieDTO> obterTodas(){
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obterTop5(){
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos(){
        return converteDados(repositorio.encontrarEpisodiosMaisRecentes());
    }

    public SerieDTO obterSerie(long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if(serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(long id){
        return converterEpisodios(repositorio.obterTodosEpisodios(id));
    }

    public List<EpisodioDTO> obterEpisodiosPorTemporada(long id, long temporada){
        return converterEpisodios(repositorio.obterEpisodiosPorTemporada(id,temporada));
    }

    public List<SerieDTO> obterSeriesPorGenero(String nomeGenero){
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteDados(repositorio.findAllByGenero(categoria));
    }

    public List<EpisodioDTO> obterTopEpisodios(long id) {
        Serie serie =  repositorio.findById(id).get();
       
        return converterEpisodios(repositorio.topEpisodiosPorSerie(serie)
            .stream()
            .limit(5)
            .collect(Collectors.toList()));

    }

    
}
