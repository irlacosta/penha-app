package br.edu.ifrs.projetoexemplomd.model;

import java.util.ArrayList;
import java.util.List;

public class Dica {

    private String id;
    private String assuntoDica;
    private String descricaoDica;
    private String userId;

    public Dica() {}

    public Dica(String assuntoDica, String descricaoDica) {
        this.assuntoDica = assuntoDica;
        this.descricaoDica = descricaoDica;
    }

    public Dica(String id, String assuntoDica, String descricaoDica) {
        this.id = id;
        this.assuntoDica = assuntoDica;
        this.descricaoDica = descricaoDica;
    }

    public Dica(String id, String assuntoDica, String descricaoDica, String userId) {
        this.id = id;
        this.assuntoDica = assuntoDica;
        this.descricaoDica = descricaoDica;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssuntoDica() {
        return assuntoDica;
    }

    public void setAssuntoDica(String assuntoDica) {
        this.assuntoDica = assuntoDica;
    }

    public String getDescricaoDica() {
        return descricaoDica;
    }

    public void setDescricaoDica(String descricaoDica) {
        this.descricaoDica = descricaoDica;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Dica{" +
                "id='" + id + '\'' +
                "assunto='" + assuntoDica + '\'' +
                ", descricao='" + descricaoDica + '\'' +
                '}';
    }
}
