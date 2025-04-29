package com.biblioteca.Principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biblioteca.Serie.Serie;
import com.biblioteca.Serie.SerieService;
import com.biblioteca.domain.ConsumoAPILivros.ConsumoApiLivros;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;
import com.biblioteca.domain.Livro.Livro;
import com.biblioteca.domain.Livro.LivroService;

public class Principal {
    private LivroService livroService;
    private SerieService serieService;

    private final String menu = """
            1- Listar livros
            2- Adicionar livro
            3- Criar serie
            4- Listar series
            5- Adicionar livro a serie
            6- Listar livros de uma serie
            0- Sair
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
            op = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Opção escolhida: " + op);

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
        Serie serie = selecionarSerie();
        System.out.println("Livros da serie: " + serie.getTitulo()+ serie.getLivros());
        for(Livro livro : serie.getLivros()) {
            System.out.println(livro);
        }
    }

    private void adicinarLivroEmSerie() {
        Serie serie = selecionarSerie();

        List<Livro> livros = criarListaLivros();
        if(livros.isEmpty()){
            System.out.println("Nenhum livro adicionado a serie.");
        }
        else{
            serie.adicionarLivros(livros);
            this.serieService.salvar(serie);
        }
    }

    public void listarLivros(){
        List<Livro> lista = this.livroService.listarTodos();
        System.out.println("Lista de livros: "+lista);
    }

    public void cadastrarLivro(){
        System.out.println("Digite o nome do livro:");
        String nomeLivro = scanner.nextLine();

        System.out.println(nomeLivro);

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
        
        List<Livro> livros = criarListaLivros();
        if(livros.isEmpty()){
            System.out.println("Nenhum livro adicionado a serie.");
        }
        else{
            serie.adicionarLivros(livros);
        }
        this.serieService.salvar(serie);
    }

    public List<Serie> listarSeries(){
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

    private List<Livro> criarListaLivros(){
        List<Livro> livros = new ArrayList<>();

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
            int op = scanner.nextInt() -1;
            scanner.nextLine();
            if(op < 0 || op >= lista.size()){
                System.out.println("Opção inválida!");
                continue;
            }
            livros.add(lista.get(op));
        } while (!nomeLivro.isBlank());

        return livros;
    }
}
