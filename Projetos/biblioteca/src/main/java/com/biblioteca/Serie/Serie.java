package com.biblioteca.Serie;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.domain.Livro.Livro;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Serie(String titulo, List<String> autores) {
        this.titulo = titulo;
        this.autores = autores;
    }

    public Serie(String titulo) {
        this.titulo = titulo;
    }

    public void adicionarLivros(List<Livro> livros) {
        for( Livro livro: livros) {
            adicionarLivro(livro);
        }
    }

    public void adicionarLivro(Livro livro) {
        if (this.livros == null) {
            this.livros = new ArrayList<>();
        }
        livro.setSerie(this);
        this.livros.add(livro);
        adicionarAutor(livro.getAutores());
    }

    public void adicionarAutor(String autor) {
        if (this.autores == null) {
            this.autores = new ArrayList<>();
        }
        else if(this.autores.contains(autor)) {
            return;
        }
        this.autores.add(autor);
    }

    @Override
    public String toString() {
        return  titulo;
    }

    

}
