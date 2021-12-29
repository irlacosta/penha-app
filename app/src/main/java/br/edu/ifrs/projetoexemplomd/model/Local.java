package br.edu.ifrs.projetoexemplomd.model;

public class Local {
    private String latitude;
    private String longitude;
    private String nome;

    public Local() {
    }

    public Local(String latitude, String longitude, String nome) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Local{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
