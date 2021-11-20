package br.edu.ifrs.projetoexemplomd.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.data.Database;
import br.edu.ifrs.projetoexemplomd.model.Feedback;
import br.edu.ifrs.projetoexemplomd.model.Pergunta;
import br.edu.ifrs.projetoexemplomd.ui.home.HomeViewModel;

import static androidx.databinding.DataBindingUtil.inflate;

public class QuizFragment extends Fragment {

    private Pergunta perguntaAtual;
    private TextView textoPergunta;
    private Button respostaSim;
    private Button respostaNao;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        //alterar o style do card no layout fragment_home
        //fazer o card ser clicável e abrir um novo fragment com o item desejada: mapa, jogo, telefones, etc.
        View root = inflate(inflater, R.layout.fragment_quiz, container, false).getRoot();

        textoPergunta = root.findViewById(R.id.texto_pergunta_quiz);
        respostaSim = root.findViewById(R.id.btn_sim);
        respostaNao = root.findViewById(R.id.btn_nao);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        perguntaAtual = Database.getProximaPergunta();
        if (perguntaAtual != null) {
            textoPergunta.setText(perguntaAtual.getTexto());
            respostaSim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSimClick();
                }

            });
            respostaNao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNaoClick();
                }
            });
        } else {
            Database.setFeedback(retornaFeedback());
            navController.navigate(R.id.nav_feedback);
        }
    }

    private Feedback buildFeedback1() {
        return new Feedback(getString(R.string.feedback_categoria1_titulo), getString(R.string.feedback_categoria1_desc), getString(R.string.feedback_pontos, Database.getPontos()));
    }

    private Feedback buildFeedback2() {
        return new Feedback(getString(R.string.feedback_categoria2_titulo), getString(R.string.feedback_categoria2_desc), getString(R.string.feedback_pontos, Database.getPontos()));
    }

    private Feedback buildFeedback3() {
        return new Feedback(getString(R.string.feedback_categoria3_titulo), getString(R.string.feedback_categoria3_desc), getString(R.string.feedback_pontos, Database.getPontos()));
    }

    private Feedback buildFeedback5() {
        return new Feedback(getString(R.string.feedback_categoria5_titulo), getString(R.string.feedback_categoria5_desc), getString(R.string.feedback_pontos, Database.getPontos()));
    }

    private Feedback retornaFeedback() {

        if (Database.getPontos() == 0) {
            return buildFeedback1();
        }
        else if(Database.getPontos() >= 1 && Database.getPontos() <= 2) {
            return buildFeedback2();
        }
        else if(Database.getPontos() >= 3 && Database.getPontos() <= 4) {
            return buildFeedback3();
        }
        else {
            return buildFeedback5();
        }
    }

    private void onSimClick() {
        Database.somaPontos(perguntaAtual.getSim());
        navController.navigate(R.id.nav_quiz);
    }

    private void onNaoClick() {
        Database.somaPontos(perguntaAtual.getNao());
        navController.navigate(R.id.nav_quiz);
    }

}
