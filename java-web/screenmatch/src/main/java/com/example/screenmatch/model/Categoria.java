package com.example.screenmatch.model;

public enum Categoria {
    ACAO("Action", "ação"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy","Comédia"),
    DRAMA("Drama","Drama"),
    ANIMACAO("Animation","Animação"),
    AVENTURA("Adventure","Aventura"),
    FANTASIA("Fantasy","Fantasia"),
    FICCAO("Sci-Fi", "Ficcao cientifica"),
    CRIME("Crime","Crime");
    
    private String categoriaOmdb;
    private String categoriaPortugues;
    
    Categoria(String categoriaOmdb, String categoriaPortugues){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Categoria fromString(String text) {
        text = text.split(",")[0].trim();
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
    
    public static Categoria fromPortugues(String text) {
        text = text.split(",")[0].trim();
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
