package com.biblioteca.domain.Serie;

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

    private String titulo;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Serie(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return  titulo;
    }

    public void informacoesSerie() {
        System.out.println("Serie: " + this.titulo);
        if (this.livros != null) {
            System.out.println("Livros na série:");
            for (Livro livro : this.getLivros()) {
                System.out.println("("+livro.getOrdemSerie()+")" + livro.getTitulo());
            }
        } else {
            System.out.println("Nenhum livro nesta série.");
        }
    }

    // RELAÇÃO - Livro

    public List<Livro> getLivros() {
        this.livros.sort((livro1, livro2) -> Double.compare(livro1.getOrdemSerie(), livro2.getOrdemSerie()));
        return this.livros;
    }

    public void adicionarLivro(Livro livro) {
        if (this.livros == null) {
            this.livros = new ArrayList<>();
        }
        else if(!this.livros.contains(livro)) {
            livro.setSerie(this);
        }
    }

    public void removerLivro(Livro livro) {
        if(this.livros != null && this.livros.contains(livro)){
            this.livros.remove(livro);
        }
    }

}
