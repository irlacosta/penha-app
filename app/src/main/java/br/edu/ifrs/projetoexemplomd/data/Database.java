package br.edu.ifrs.projetoexemplomd.data;

import java.util.List;

import br.edu.ifrs.projetoexemplomd.model.Feedback;
import br.edu.ifrs.projetoexemplomd.model.Pergunta;

public final class Database {
    public static List<Pergunta> perguntas;
    private static int perguntaAtualIndex = 0;
    private static int somaPontos;
    private static Feedback feedback;

    public static Feedback getFeedback() {
        return feedback;
    }

    public static void setFeedback(Feedback feedback) {
        Database.feedback = feedback;
    }

    public static void somaPontos(int somaPontos) {
        Database.somaPontos += somaPontos;
    }

    public static int getPontos() {
        return Database.somaPontos;
    }

    public static void salvarPerguntas(List<Pergunta> lista) {
        perguntas = lista;
    }

    public static List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public static Pergunta getProximaPergunta() {
        try {
            Pergunta proximaPergunta = perguntas.get(perguntaAtualIndex);
            perguntaAtualIndex++;
            return proximaPergunta;
        } catch (IndexOutOfBoundsException e) {
            perguntaAtualIndex = 0;
            return null;
        }
    }

    public static void voltarPergunta(){
        perguntaAtualIndex = 0;
        somaPontos = 0;
    }
}
