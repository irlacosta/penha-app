package br.edu.ifrs.projetoexemplomd.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Telefone implements Parcelable {
    private String nome;
    private String numero;

    public Telefone() {
    }

    public Telefone(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public Telefone (Parcel in) {
        String[] data = new String[2];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method

        this.nome = data[0];
        this.numero = data[1];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {nome, numero});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Telefone createFromParcel(Parcel in) {
            return new Telefone(in);
        }

        public Pergunta[] newArray(int size) {
            return new Pergunta[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}
