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
        telefones.add(new Telefone("Central de Atendimento à Mulher", "180"));
        telefones.add(new Telefone("Polícia Militar", "190"));
        telefones.add(new Telefone("Polícia Civil", "197"));
        telefones.add(new Telefone("SAMU", "192"));
        telefones.add(new Telefone("Porto Alegre -  Plantão ","(51) 3288-2172, (51) 3288-2284, (51) 3288-2289"));
        telefones.add(new Telefone("Delegacia da Mulher - Porto Alegre - RS",  "32882172"));
        telefones.add(new Telefone("Porto Alegre - Coordenação das DEAMs Av. João Pessoa, 2050","(51) 3288-2309"));
        telefones.add(new Telefone("Porto Alegre - Palácio da Polícia Rua Freitas e Castro","(51) 3288-2172"));
        telefones.add(new Telefone("Porto Alegre -  Secretaria (para informações de procedimentos)","(51)3288-2173"));
        telefones.add(new Telefone("Bento Gonçalves - Rua Treze de Maio, 222","(54) 3452-3200"));
        telefones.add(new Telefone("Canoas - Rua Cândido Machado, 106","(51) 3472-0494"));
        telefones.add(new Telefone("Caxias do Sul - Rua Dr. Montauri, 1387","(54) 3221-1357"));
        telefones.add(new Telefone("Cruz Alta - Rua Coronel José Gabriel, 21","(55) 3322-1864"));
        telefones.add(new Telefone("Erechim - Rua Flores da Cunha, 91","(54) 3321-9996"));
        telefones.add(new Telefone("Gravataí - RS 030, 1013 – Parque dos Anjos","(51) 3431-5274"));
        telefones.add(new Telefone("Ijuí - Rua Barros Cassal, 516","(53) 3222-6888"));
        telefones.add(new Telefone("Lajeado - Rua João Batista de Melo, 509","(51) 3714-3309"));
        telefones.add(new Telefone("Novo Hamburgo - Rua Graça Aranha, 55","(51) 3584-5815"));
        telefones.add(new Telefone("Passo Fundo - Av. Dr. Cesar Santos, 106","(54) 3581-0725"));
        telefones.add(new Telefone("Pelotas - Rua Barros Cassal, 516","(53) 3222-6888"));
        telefones.add(new Telefone("Rio Grande - Rua Almirante Barroso, 142","(53) 3293-1420"));
        telefones.add(new Telefone("Santa Cruz do Sul - Rua João Werlang, 569","(51) 3711-4513"));
        telefones.add(new Telefone("Santa Maria - Rua Duque de Caxias, 1196","(55) 3222-9646"));
        telefones.add(new Telefone("Santa Rosa - Rua Palmeiras, 229","(55) 3512-5911"));
        telefones.add(new Telefone("São Leopoldo - Rua São Paulo, 970 – Centro","(51) 3591-3334"));
        return telefones;
    }
}
