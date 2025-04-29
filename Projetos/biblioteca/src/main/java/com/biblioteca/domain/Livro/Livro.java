package com.biblioteca.domain.Livro;

import com.biblioteca.Serie.Serie;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    private String autores;
    private String editora;
    private String dataPublicacao;

    private int paginas;
    private String categorias;
    private String imagem;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    private int ordemSerie;

    public Livro(DadosLivro dados){
        this.titulo = dados.getTitulo();
        this.autores = dados.getAutores();
        this.editora = dados.getEditora();
        this.dataPublicacao = dados.getDataPublicacao();
        this.paginas = dados.getPaginas();
        this.categorias = dados.getCategorias();
        this.imagem = dados.getImagem();
        this.isbn = dados.getIsbn13() != null ? dados.getIsbn13() : dados.getIsbn10();
    }

    @Override
    public String toString() {
        return "Livro [titulo=" + titulo + "]";
    }

    
    
}
