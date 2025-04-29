package com.biblioteca.domain.Serie;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.domain.Autor.Autor;
import com.biblioteca.domain.Livro.Livro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "serie_autor",
        joinColumns = @JoinColumn(name = "serie_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    private String titulo;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Serie(String titulo, List<Autor> autores) {
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

    public void adicionarAutor(List<Autor> autores) {
        if (this.autores == null) {
            this.autores = new ArrayList<>();
        }
        for( Autor autor : autores) {
            if (!this.autores.contains(autor)) {
                this.autores.add(autor);
            }
        }
    }

    @Override
    public String toString() {
        return  titulo;
    }

}
