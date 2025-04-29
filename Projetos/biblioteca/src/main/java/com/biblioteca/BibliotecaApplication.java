package com.biblioteca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biblioteca.Principal.Principal;
import com.biblioteca.domain.Livro.LivroService;
import com.biblioteca.domain.Serie.SerieService;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	@Autowired
	LivroService livroService;

	@Autowired
	SerieService serieService;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(livroService, serieService);
		principal.inicio();
	}

}
