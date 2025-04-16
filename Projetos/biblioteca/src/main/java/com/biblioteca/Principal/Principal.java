package com.biblioteca.Principal;

import java.util.List;
import java.util.Scanner;

import com.biblioteca.domain.ConsumoAPILivros.ConsumoApiLivros;
import com.biblioteca.domain.ConsumoAPILivros.DadosLivro;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Digite o nome do livro:");
        Scanner scanner = new Scanner(System.in);
        String nomeLivro = scanner.nextLine();

        ConsumoApiLivros consumoAPI = new ConsumoApiLivros();
        List<DadosLivro> livros = consumoAPI.pesquisarTitulo(nomeLivro);

        livros.forEach(System.out::println);
    }
}
