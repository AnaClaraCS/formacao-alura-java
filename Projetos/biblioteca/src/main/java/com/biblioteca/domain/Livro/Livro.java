package com.biblioteca.domain.Livro;

import com.biblioteca.Serie.Serie;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String titulo;
    private String autores;
    private String editora;
    private String dataPublicacao;
    private String descricao;
    private int paginas;
    private String categorias;
    private String imagem;
    private String isbn;

    @ManyToOne
    private Serie serie;

    public Livro(DadosLivro dados){
        this.titulo = dados.getTitulo();
        this.autores = dados.getAutores();
        this.editora = dados.getEditora();
        this.dataPublicacao = dados.getDataPublicacao();
        this.descricao = dados.getDescricao();
        this.paginas = dados.getPaginas();
        this.categorias = dados.getCategorias();
        this.imagem = dados.getImagem();
        this.isbn = dados.getIsbn13() != null ? dados.getIsbn13() : dados.getIsbn10();
    }
    
}
