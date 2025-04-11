package com.example.screenmatch.model;

import java.util.List;
import java.util.OptionalDouble;

import com.example.screenmatch.service.traducao.ConsultaMyMemory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "series")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String titulo;

    private Integer totalTemporadas;
    private double avaliacao;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String sinopse;
    private String poster;
    private String atores;  

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<Episodio> episodios;
    
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse()).trim();
    }

    public Serie() {
    }

    public String toString(){
        return this.genero + " - " + this.titulo + " - " + this.avaliacao;
    }

    public void setEpsodios(List<Episodio> episodios){
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    public List<Episodio> getEpisodios(){
        return this.episodios;
    }

    public void informacoes(){
        System.out.println(" ------ Informações da serie ------");
            System.out.println("Série: " + this.toString());
            System.out.println("Sinopse: " + getSinopse());
            System.out.println("Atores: " + getAtores());
            System.out.println("Poster: " + getPoster());
            System.out.println("Avaliação: " + getAvaliacao());
            System.out.println("Total de temporadas: " + getTotalTemporadas());
    }
    
}

