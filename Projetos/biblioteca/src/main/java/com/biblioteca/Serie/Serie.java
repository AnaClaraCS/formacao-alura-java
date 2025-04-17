package com.biblioteca.Serie;

import java.util.List;

import com.biblioteca.domain.Livro.Livro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Serie")
@Table(name = "series")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private List<String> autores;

    private String titulo;

    @OneToMany
    private List<Livro> livros;

    public Serie(String titulo, List<String> autores) {
        this.titulo = titulo;
        this.autores = autores;
    }

    public Serie(String titulo) {
        this.titulo = titulo;
    }

    public void addLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
