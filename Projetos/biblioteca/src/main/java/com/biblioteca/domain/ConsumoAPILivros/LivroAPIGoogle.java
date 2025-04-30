package com.biblioteca.domain.ConsumoAPILivros;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroAPIGoogle {
    private String titulo;
    private String autores;
    private String editora;
    private String dataPublicacao;
    private int paginas;
    private String categorias;
    private String imagem;
    private String isbn10;
    private String isbn13;

    public LivroAPIGoogle( LivroResponseDTO dto){

        this.titulo = dto.volumeInfo().titulo();
        
        List<String> aut = dto.volumeInfo().autores();
        this.autores = (aut != null) ? String.join(", ", aut) : "Autor desconhecido";        
        this.editora = dto.volumeInfo().editora();
        this.dataPublicacao = dto.volumeInfo().dataPublicacao();
        this.paginas = dto.volumeInfo().paginas();

        List<String> cat = dto.volumeInfo().categorias();
        this.categorias = (cat != null) ? String.join(", ", cat) : "Sem categoria"; 
        this.imagem = (dto.volumeInfo().imagem() != null && dto.volumeInfo().imagem().thumbnail() != null)
            ? dto.volumeInfo().imagem().thumbnail()
            : "Sem imagem";
        if( dto.volumeInfo().industryIdentifiers() != null){    
            for (IndustryIdentifier id : dto.volumeInfo().industryIdentifiers()) {
                if (id.tipo().equals("ISBN_10")) {
                    this.isbn10 = id.identificador();
                } else if (id.tipo().equals("ISBN_13")) {
                    this.isbn13 = id.identificador();
                }
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
