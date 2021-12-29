package br.edu.ifrs.projetoexemplomd.model;

import java.util.ArrayList;
import java.util.List;

public class Dica {

    private String fraseDica;
    private String saibaMaisDica;
    private String url;

    public Dica() {}

    public Dica(String fraseDica, String saibaMaisDica) {
        this.fraseDica = fraseDica;
        this.saibaMaisDica = saibaMaisDica;
    }

    public Dica(String fraseDica, String saibaMaisDica, String url) {
        this.fraseDica = fraseDica;
        this.saibaMaisDica = saibaMaisDica;
        this.url = url;
    }

    public String getFraseDica() {
        return fraseDica;
    }

    public void setFraseDica(String fraseDica) {
        this.fraseDica = fraseDica;
    }

    public String getSaibaMaisDica() {
        return saibaMaisDica;
    }

    public void setSaibaMaisDica(String saibaMaisDica) {
        this.saibaMaisDica = saibaMaisDica;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Dica{" +
                "fraseDica='" + fraseDica + '\'' +
                ", saibaMaisDica='" + saibaMaisDica + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
