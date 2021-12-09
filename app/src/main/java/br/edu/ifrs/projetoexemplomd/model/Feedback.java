package br.edu.ifrs.projetoexemplomd.model;

public class Feedback {

    private String categoria;
    private String descricao;
    private String pontuacao;

    public Feedback() {  }

    public Feedback(String categoria, String descricao, String pontuacao) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.pontuacao = pontuacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }
}
