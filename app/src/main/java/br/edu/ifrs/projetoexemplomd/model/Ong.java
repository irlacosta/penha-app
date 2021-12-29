package br.edu.ifrs.projetoexemplomd.model;

public class Ong {

    private String nome;
    private String endereco;
    private String url;

    public Ong() {
    }

    public Ong(String nome, String endereco, String url) {
        this.nome = nome;
        this.endereco = endereco;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Ong{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
