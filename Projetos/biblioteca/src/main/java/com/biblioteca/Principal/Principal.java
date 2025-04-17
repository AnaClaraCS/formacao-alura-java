package com.biblioteca.Principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.biblioteca.Serie.Serie;
import com.biblioteca.domain.ConsumoAPILivros.ConsumoApiLivros;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;
import com.biblioteca.domain.Livro.Livro;
import com.biblioteca.domain.Livro.LivroService;

public class Principal {
    private LivroService livroService;

    private final String menu = """
            1- Listar livros
            2- Adicionar livro
            3- Criar serie
            """;

    private Scanner scanner = new Scanner(System.in);

    public Principal(LivroService livroService){
        this.livroService = livroService;
    }

    public void inicio() {

        


        
    }

    public void listarLivros(){
        List<Livro> lista = this.livroService.listarTodos();
        System.out.println("Lista de livros: "+lista);
    }

    public void cadastrarLivro(){
        System.out.println("Digite o nome do livro:");
        String nomeLivro = scanner.nextLine();

        ConsumoApiLivros consumoAPI = new ConsumoApiLivros();
        List<DadosLivro> livros = consumoAPI.pesquisarTitulo(nomeLivro);

        for (int i = 0; i < livros.size(); i++) {
            DadosLivro livro = livros.get(i);
            System.out.println("Livro " + (i + 1) + ": " + livro);
        }

        System.out.println("Qual livro deseja salvar? ");
        int op = scanner.nextInt() -1;
        scanner.nextLine();


        Livro livroEscolhido = livroService.salvar(new Livro(livros.get(op)));

        System.out.println("Livro salvo com sucesso: " + livroEscolhido);
    }

    public void cadrastrarSerie(){
        System.out.println("Digite o nome da serie:");
        String nome = scanner.nextLine();

        Serie serie = new Serie(nome);

        List<Livros> lista;

        do{
            System.out.println("Digite o nome do livro:");
            String nomeLivro = scanner.nextLine();
            Livro livro = livroService.buscarPorTitulo(nomeLivro);
            
        }
        
    }
}
