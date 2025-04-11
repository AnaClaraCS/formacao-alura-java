package com.example.screenmatch.principal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.example.screenmatch.model.Categoria;
import com.example.screenmatch.model.DadosSerie;
import com.example.screenmatch.model.DadosTemporada;
import com.example.screenmatch.model.Episodio;
import com.example.screenmatch.model.Serie;
import com.example.screenmatch.repository.SerieRepository;
import com.example.screenmatch.service.ConsumoAPI;
import com.example.screenmatch.service.ConverterDados;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverterDados conversor = new ConverterDados();

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    private SerieRepository serieRepository;

    private String menuSerie= """
        \n-----------------------------------
        Escolha uma opção: 
        1 - Listar temporadas
        2 - Listar top 5 episódios
        3 - Listar episódios depois de um ano
        4 - Estatísticas dos episódios
        5 - Estatísticas por temporada
        6 - Buscar Epsiodio por trecho
        0 - Sair
        Digite a opção desejada:  
    """;

    private String menu = """
            \n ---------------------------
            Escolha uma opção:
            1 - Listar todas as series
            2 - Listar top 5 series
            3 - Cadastrar nova serie no banco
            4 - Ver informações de uma serie
            5 - Buscar por categoria
            6 - Buscar series de um ator / atriz
            7 - Filtrar series por temporada e avaliação
            0 - Encerrar
            Digite a opção desejada:  
            """;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {

        int opcao;
        do{
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarTodasSeries();
                    break;

                case 2:
                    listarTop5Series();
                    break;

                case 3:
                    salvarSerieNoBanco();
                    break;
                case 4:
                    menuOpcoesSerie();
                    break;

                case 5:
                    buscarPorCategoria();
                    break;

                case 6:
                    buscarPorAtor();
                    break;

                case 7:
                    seriesPorTemporadaEAvaliacao();
                    break;
                    
                case 0:
                    System.out.println("Saindo...");
                    break;
                    
                default:
                    System.out.println("Opção inválida");
                    break;
                }
                
            }while(opcao!=0);
            
        }

        public void listarTodasSeries(){
        System.out.println("\n ---- Series ----");
        List<Serie> series = serieRepository.findAll();
        series.forEach(System.out::println);
    }
    
    public void listarTop5Series(){
        List<Serie> series = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        System.out.println("\n ---- Top 5 Series ----");
        series.forEach(System.out::println);
    }
    
    public void salvarSerieNoBanco(){
        System.out.println("Digite o nome da série: ");
        String nomeSerie = scanner.nextLine();
        
        nomeSerie = nomeSerie.replace(" ", "+");
        String json = consumoAPI.obterDados(ENDERECO + nomeSerie + API_KEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        Serie serie = new Serie(dadosSerie);
        
        List<DadosTemporada> temporadas = new ArrayList<>();
        for(int i =1; i<=serie.getTotalTemporadas(); i++) {
            json = consumoAPI.obterDados(ENDERECO + nomeSerie + "&season=" + i + API_KEY);
            DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }
        
        List<Episodio> episodios = temporadas.stream()
        .flatMap(t -> t.episodios().stream().map(e -> new Episodio(t.numero(), e)))
        .toList();
        
        serie.setEpsodios(episodios);
        serieRepository.save(serie);
    }
    
    public void buscarPorCategoria(){
        System.out.println("Digite o nome da categoria: ");
        String nomeCategoria = scanner.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeCategoria);
        List<Serie> series = serieRepository.findByGenero(categoria);
        series.forEach(System.out::println);
    }
    
    public void buscarPorAtor(){
        System.out.println("Digite o nome do ator / da atriz: ");
        String nomeAtor = scanner.nextLine();
        
        List<Serie> series = serieRepository.findAllByAtoresContainsIgnoreCase(nomeAtor);
        System.out.println(" ---- Series com "+nomeAtor+" ----");
        series.forEach(System.out::println);
    }
    
    public void menuOpcoesSerie() {
        System.out.println("Digite o nome da série: ");
        String nomeSerie = scanner.nextLine();
        
        Serie serie = serieRepository.findByTituloContainsIgnoreCase(nomeSerie);
        serie.informacoes();

        int opcao = 0;
        do {
            System.out.println(menuSerie);
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcao) {
                case 1:
                    listarEpisodios(serie);
                    break;
                case 2:
                    listarTop5Episodios(serie);
                    break;
                case 3:
                    episodiosDepoisDeAno(serie);
                    break;
                case 4:
                    estatiscaEpisodios(serie);
                    break;
                case 5:
                    estatisticasPorTemporada(serie);
                    break;
                
                case 6:
                    buscarEpisodioPorTrecho(serie);
                    break;                

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }

        } while(opcao != 0);
    }  
    
    public void listarEpisodios(Serie serie) {
        List<Episodio> episodios = serie.getEpisodios();
        System.out.println("\n ---- Episodios ----");
        episodios.forEach(System.out::println);
        
    }

    public void listarTop5Episodios(Serie serie) {
        System.out.println("\n ---- Top 5 episódios ----");
        List<Episodio> episodios = serieRepository.topEpisodiosPorSerie(serie);
        episodios.stream().limit(5).forEach(System.out::println);

    }
    
    public void episodiosDepoisDeAno(Serie serie) {
        System.out.println("A partir de que ano deseja ver os episódios? ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\n ---- Epsiodio a partir de  " + ano + " ----");
        LocalDate data = LocalDate.of(ano, 1, 1);
        List<Episodio> episodios = serieRepository.episodiosAposData(serie, data);
        episodios.forEach(System.out::println);
    }

    public void estatiscaEpisodios(Serie serie){
        DoubleSummaryStatistics est = serie.getEpisodios().stream()
            .mapToDouble(Episodio::getavaliacao)
            .filter(e -> e > 0)
            .summaryStatistics();
        System.out.println("\n ---- Estatisticas das avaliações do espisodios ----");
        System.out.println("Média: " + est.getAverage());
        System.out.println("Mínimo: " + est.getMin());
        System.out.println("Máximo: " + est.getMax());
        System.out.println("Quantidade: " + est.getCount());
    }

    public void estatisticasPorTemporada(Serie serie){
        System.out.println("\n ---- Estatisticas por temporada ----");
        serie.getEpisodios().stream()
            .filter(e -> e.getavaliacao() > 0)
            .collect(Collectors.groupingBy(Episodio::getTemporada, Collectors.summarizingDouble(Episodio::getavaliacao)))
            .forEach((temporada, est) -> System.out.println("Temporada " + temporada + ": " + est.getAverage())            );

    }
    
    public void seriesPorTemporadaEAvaliacao(){
        System.out.println("Digite o número máximo de temporadas: ");
        int temporadasMaximas = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite a avaliação miníma: ");
        double avaliacaoMinima = scanner.nextDouble();
        scanner.nextLine();

        List<Serie> series = serieRepository.findAllByAvaliacaoGreaterThanAndTotalTemporadasLessThan(avaliacaoMinima, temporadasMaximas);
        series.forEach(System.out::println);
    }

    public void buscarEpisodioPorTrecho(Serie serie){
        System.out.println("Digite o trecho do nome do episodio: ");
        String trecho = scanner.nextLine();

        List<Episodio> episodios = serieRepository.episodioPorTrecho(serie,trecho);
        episodios.forEach(System.out::println);
    }
}
