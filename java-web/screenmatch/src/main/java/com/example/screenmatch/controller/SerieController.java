package com.example.screenmatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SerieDTO;
import com.example.screenmatch.model.EpisodioDTO;
import com.example.screenmatch.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> obterSeries() {
        return service.obterTodas();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5(){
        return service.obterTop5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterlancamentos(){
        return service.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterSerie(@PathVariable long id){
        return service.obterSerie(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable long id){
        return service.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{temporada}")
    public List<EpisodioDTO> obterEpisodiosPorTemporada(@PathVariable long id, @PathVariable long temporada){
        return service.obterEpisodiosPorTemporada(id, temporada);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorGenero(@PathVariable String nomeGenero){
        return service.obterSeriesPorGenero(nomeGenero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTop5Episodio(@PathVariable long id){
        return service.obterTopEpisodios(id);
    }
}