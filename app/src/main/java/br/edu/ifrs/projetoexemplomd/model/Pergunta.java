package br.edu.ifrs.projetoexemplomd.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pergunta implements Parcelable {

    private String texto;
    private int id;
    private int sim;
    private int nao;

    public Pergunta() {};

    public Pergunta(String texto, int id, int sim, int nao) {
        this.texto = texto;
        this.id = id;
        this.sim = sim;
        this.nao = nao;
    }
    public Pergunta (Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method

        this.texto = data[0];
        this.id = Integer.valueOf(data[1]);
        this.sim = Integer.valueOf(data[2]);
        this.nao = Integer.valueOf(data[3]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {texto,
                String.valueOf(this.id),
                String.valueOf(this.sim),
                String.valueOf(this.nao)});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pergunta createFromParcel(Parcel in) {
            return new Pergunta(in);
        }

        public Pergunta[] newArray(int size) {
            return new Pergunta[size];
        }
    };
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSim() {
        return sim;
    }

    public void setSim(int sim) {
        this.sim = sim;
    }

    public int getNao() {
        return nao;
    }

    public void setNao(int nao) {
        this.nao = nao;
    }

}
