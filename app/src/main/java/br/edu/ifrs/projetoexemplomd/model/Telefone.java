package br.edu.ifrs.projetoexemplomd.model;

import java.util.ArrayList;
import java.util.List;

public class Telefone {
    private String idTelefone;
    private String localTelefone;
    private String numeroTelefone;

    public Telefone(String localTelefone, String numeroTelefone) {
        this.localTelefone = localTelefone;
        this.numeroTelefone = numeroTelefone;
    }

    public String getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(String idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getLocalTelefone() {
        return localTelefone;
    }

    public void setLocalTelefone(String localTelefone) {
        this.localTelefone = localTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "id='" + idTelefone + '\'' +
                "nome='" + localTelefone + '\'' +
                ", numero='" + numeroTelefone + '\'' +
                '}';
    }

    public static List<Telefone> inicializaLista() {
        List<Telefone> telefones = new ArrayList<>();
        telefones.add(new Telefone("Delegacia da Mulher - Porto Alegre - RS",  "32882172"));
        telefones.add(new Telefone("Central de Atendimento à Mulher", "180"));
        telefones.add(new Telefone("Polícia Militar", "190"));
        telefones.add(new Telefone("Polícia Civil", "197"));
        telefones.add(new Telefone("SAMU", "192"));
        return telefones;
    }
}
