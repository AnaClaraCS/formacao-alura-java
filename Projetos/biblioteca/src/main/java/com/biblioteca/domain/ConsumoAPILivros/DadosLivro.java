package com.biblioteca.domain.ConsumoAPILivros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosLivro {
    private String titulo;
    private String autores;
    private String editora;
    private String dataPublicacao;
    private int paginas;
    private String categorias;
    private String imagem;
    private String isbn10;
    private String isbn13;

    public DadosLivro( LivroResponseDTO dto){

        this.titulo = dto.volumeInfo().titulo();
        this.autores = String.join(", ", dto.volumeInfo().autores());
        this.editora = dto.volumeInfo().editora();
        this.dataPublicacao = dto.volumeInfo().dataPublicacao();
        this.paginas = dto.volumeInfo().paginas();
        this.categorias = String.join(", ", dto.volumeInfo().categorias());
        this.imagem = (dto.volumeInfo().imagem() != null && dto.volumeInfo().imagem().thumbnail() != null)
            ? dto.volumeInfo().imagem().thumbnail()
            : "Sem imagem";
        for (IndustryIdentifier id : dto.volumeInfo().industryIdentifiers()) {
            if (id.tipo().equals("ISBN_10")) {
                this.isbn10 = id.identificador();
            } else if (id.tipo().equals("ISBN_13")) {
                this.isbn13 = id.identificador();
            }
        }        
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + "\n" +
               "Autores: " + autores + "\n" +
               "Editora: " + editora + "\n" ;
    }
}
