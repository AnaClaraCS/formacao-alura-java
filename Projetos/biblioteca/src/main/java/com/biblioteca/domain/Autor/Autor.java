package com.biblioteca.domain.Autor;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.domain.Livro.Livro;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Autor")
@Table(name = "autores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Autor(String nome) {
        this.nome = nome;
    }

    public void adicionarLivros(List<Livro> livros) {
        for (Livro livro : livros) {
            adicionarLivro(livro);
        }
    }

    private void adicionarLivro(Livro livro) {
        if(this.livros == null) {
            this.livros = new ArrayList<>();
        }
        else if(!this.livros.contains(livro)) {
            this.livros.add(livro);
        }
    }

}
