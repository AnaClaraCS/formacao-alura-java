package com.biblioteca.domain.Livro;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.domain.Autor.Autor;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;
import com.biblioteca.domain.Serie.Serie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Livro")
@Table(name = "livros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String editora;
    private String dataPublicacao;

    private int paginas;
    private String categorias;
    private String imagem;
    private String isbn;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    private int ordemSerie;

    public Livro(DadosLivro dados){
        this.titulo = dados.getTitulo();
        this.editora = dados.getEditora();
        this.dataPublicacao = dados.getDataPublicacao();
        this.paginas = dados.getPaginas();
        this.categorias = dados.getCategorias();
        this.imagem = dados.getImagem();
        this.isbn = dados.getIsbn13() != null ? dados.getIsbn13() : dados.getIsbn10();
    }

    @Override
    public String toString() {
        return "Livro [titulo=" + titulo +" - " +autores+ "]";
    }

    public void adicionarAutores(String autoresNome){
        if(autores == null){
            this.autores = new ArrayList<>();
        }
        for( String nomeAutor:  List.of(autoresNome.split(","))){
            Autor novoAutor = new Autor(nomeAutor);
            if(!this.autores.contains(novoAutor)){
                this.autores.add(novoAutor);
            }
        }
    }

    
    
}
