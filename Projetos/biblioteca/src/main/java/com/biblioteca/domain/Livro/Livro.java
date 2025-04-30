package com.biblioteca.domain.Livro;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.domain.Autor.Autor;
import com.biblioteca.domain.ConsumoAPILivros.LivroAPIGoogle;
import com.biblioteca.domain.Serie.Serie;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    private int paginas;
    private String categorias;
    private String imagem;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    @ManyToOne
    @JoinColumn(name = "serie_id")
    private Serie serie;

    private double ordemSerie;

    public Livro(LivroAPIGoogle dados){
        this.titulo = dados.getTitulo();
        this.editora = dados.getEditora();
        this.paginas = dados.getPaginas();
        this.categorias = dados.getCategorias();
        this.imagem = dados.getImagem();
        adicionarAutores(dados.getAutores());
    }

    @Override
    public String toString() {
        return titulo +" ( " + autores + " )";
    }

    public void informacoesLivro(){
        System.out.println("Titulo: " + titulo);
        System.out.println("Autores: ");
        for( Autor autor : autores){
            System.out.println(autor.getNome());
        }
        if(serie != null){
            System.out.println("Serie: " + serie.getTitulo() + " - Ordem: " + ordemSerie);
        }
        System.out.println("Editora: " + editora);
        System.out.println("Categorias: " + categorias);
        System.out.println("Paginas: " + paginas);
        System.out.println("Imagem: " + imagem);
    }

    // RELAÇÃO - Autor

    private void adicionarAutores(String autoresNome){
        for( String nomeAutor:  List.of(autoresNome.split(","))){
            Autor novoAutor = new Autor(nomeAutor);
            adicionarAutor(novoAutor);
        }
    }

    public void adicionarAutor(Autor autor){
        if(this.autores == null){
            this.autores = new ArrayList<>();
        }
        if(!this.autores.contains(autor)){
            this.autores.add(autor);
            autor.adicionarLivro(this);
        }
    }

    public void removerAutor(Autor autor){
        if(this.autores != null && this.autores.contains(autor)){
            this.autores.remove(autor);
            autor.removerLivro(this);
        }
    }

    // RELAÇÃO - Serie

    public void definirSerie(Serie serie, double ordemSerie){
        this.serie = serie;
        this.ordemSerie = ordemSerie;
        serie.adicionarLivro(this);
    }

    public void removerSerie(){
        if (this.serie != null){
            serie.removerLivro(this);
        }
        this.serie = null;
        this.ordemSerie = 0;
    }
    
}
