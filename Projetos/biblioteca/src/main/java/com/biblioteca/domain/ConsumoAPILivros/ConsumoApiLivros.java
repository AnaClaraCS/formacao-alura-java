package com.biblioteca.domain.ConsumoAPILivros;

import java.util.stream.Collectors;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsumoApiLivros {
    
    private static final String URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private ObjectMapper mapper = new ObjectMapper();
    
    public List<DadosLivro> pesquisarTitulo(String titulo){
        titulo = titulo.replace(" ", "%20");

        ConsumoAPI consumoAPI = new ConsumoAPI();
        String json = consumoAPI.obterJson(URL + titulo);
        try {
            ResponseApiDTO response = mapper.readValue(json, ResponseApiDTO.class);
            List<DadosLivro> livros = response.livros().stream().map( l -> new DadosLivro(l)).collect(Collectors.toList());
            return livros;
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
