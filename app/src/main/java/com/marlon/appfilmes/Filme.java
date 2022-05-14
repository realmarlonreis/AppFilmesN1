package com.marlon.appfilmes;

public class Filme {

    public int id;

    public String nome, genero, categoria, nacionalidade;

    public Filme() {

    }

    public Filme(String nome, String genero, String categoria, String nacionalidade) {
        this.nome = nome;
        this.genero = genero;
        this.categoria = categoria;
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return nome + "  |  " + genero + "  |  " + categoria + " | " + nacionalidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getCategoria() { return categoria;}

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNacionalidade() { return nacionalidade;}

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

}
