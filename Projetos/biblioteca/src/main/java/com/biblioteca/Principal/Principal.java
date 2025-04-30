package com.biblioteca.Principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.biblioteca.domain.ConsumoAPILivros.ConsumoApiLivros;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;
import com.biblioteca.domain.Livro.Livro;
import com.biblioteca.domain.Livro.LivroService;
import com.biblioteca.domain.Serie.Serie;
import com.biblioteca.domain.Serie.SerieService;

@Component
public class Principal {
    @Autowired
    private LivroService livroService;
    @Autowired
    private SerieService serieService;

    private final String menu = """
        ------------------------------------------------
            1- Listar livros
            2- Adicionar livro
            3- Criar serie
            4- Listar series
            5- Adicionar livro a serie
            6- Listar livros de uma serie
            0- Sair
        ------------------------------------------------
            """;

    private Scanner scanner = new Scanner(System.in);

    public Principal(LivroService livroService, SerieService serieService) {
        this.serieService = serieService;
        this.livroService = livroService;
    }

    public void inicio() {
        int op;

        do{
            System.out.println(menu);
            System.out.println("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> listarLivros();
                case 2 -> cadastrarLivro();
                case 3 -> cadrastrarSerie();
                case 4 -> listarSeries();
                case 5 -> adicinarLivroEmSerie();
                case 6 -> listarLivrosSerie();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }while (op != 0); 
    }

    private void listarLivrosSerie() {
        System.out.println(" ****** Listar livros de uma serie ****** ");
        Serie serie = selecionarSerie();
        serie.informacoesSerie();
    }

    private void adicinarLivroEmSerie() {
        System.out.println(" ****** Adicionar livros em serie ****** ");
        Serie serie = selecionarSerie();
        adicionarLivrosEmSerie(serie);
        this.serieService.salvar(serie);
    }

    public void listarLivros(){
        System.out.println(" ****** Lista de livros ****** ");
        List<Livro> lista = this.livroService.listarTodos();
        for(int i=0; i<lista.size(); i++){
            Livro livro = lista.get(i);
            System.out.println(i +" - " + livro);
        }
    }

    public void cadastrarLivro(){
        System.out.println(" ****** Cadastrar livro ****** ");
        System.out.println("Digite o nome do livro:");
        String nomeLivro = scanner.nextLine();

        ConsumoApiLivros consumoAPI = new ConsumoApiLivros();
        List<DadosLivro> livros = consumoAPI.pesquisarTitulo(nomeLivro);

        for (int i = 0; i < livros.size(); i++) {
            DadosLivro livro = livros.get(i);
            System.out.println("Livro " + (i + 1) + ": " + livro);
        }

        System.out.println("Qual livro deseja salvar? (0 para nenhum)");
        int op = scanner.nextInt();
        scanner.nextLine();

        if(op != 0 && op <= livros.size()){
            op = op - 1;
            Livro livroEscolhido = livroService.salvar(new Livro(livros.get(op)));
            System.out.println("Livro salvo com sucesso: " + livroEscolhido);
        }
        else{
            System.out.println("Nenhum livro salvo");
        }

    }
    
    public void cadrastrarSerie(){
        System.out.println(" ****** Cadastrar serie ****** ");
        System.out.println("Digite o nome da serie:");
        String nome = scanner.nextLine();
        Serie serie = new Serie(nome);
        this.serieService.salvar(serie);
        adicionarLivrosEmSerie(serie);
    }

    public List<Serie> listarSeries(){
        System.out.println(" ****** Lista de series ****** ");
        List<Serie> lista = this.serieService.listarTodos();

        if(lista.isEmpty()){
            System.out.println("Nenhuma serie cadastrada.");
            return null;
        }
        System.out.println("Lista de series: ");
        for (int i = 0; i < lista.size(); i++) {
            Serie serie = lista.get(i);
            System.out.println("Serie " + (i + 1) + ": " + serie);
        }

        return lista;
    }

    private Serie selecionarSerie(){
        List<Serie> lista = listarSeries();
        if(lista == null) return null;
        System.out.println("Escolha uma serie: ");
        int op = scanner.nextInt() -1;
        scanner.nextLine();
        if(op < 0 || op >= lista.size()){
            System.out.println("Opção inválida!");
            return null;
        }
        return lista.get(op);
    }

    private void adicionarLivrosEmSerie(Serie serie){

        String nomeLivro;
        
        do{
            System.out.println("Digite o nome do livro:");
            nomeLivro = scanner.nextLine();

            List<Livro> lista = livroService.buscarPorTitulo(nomeLivro);
            for (int i = 0; i < lista.size(); i++) {
                Livro livro = lista.get(i);
                System.out.println("Livro " + (i + 1) + ": " + livro);
            }

            System.out.println("Qual livro deseja adicionar a serie? ");
            int op = scanner.nextInt();
            scanner.nextLine();

            if(op == 0 || op > lista.size()){
                System.out.println("Nenhum livro salvo");
                continue;
            }

            op = op - 1;
            Livro livroEscolhido = lista.get(op);
            System.out.println("Qual a ordem do livro na serie? ");
            int ordem = scanner.nextInt();
            scanner.nextLine();

            this.livroService.adicionarSerie(livroEscolhido, serie, ordem);
        } while (!nomeLivro.isBlank());

    }

}
