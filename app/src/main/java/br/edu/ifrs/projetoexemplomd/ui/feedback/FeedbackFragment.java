package br.edu.ifrs.projetoexemplomd.ui.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.data.Database;

public class FeedbackFragment extends Fragment {

    private Button finalizaBtn;
    private NavController navController;
    private TextView categoria;
    private TextView descricao;
    private TextView pontuacao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        setClick();
        setText();
    }

    public void bind(View view) {
        finalizaBtn = view.findViewById(R.id.btn_finalizar_feedback);
        categoria = view.findViewById(R.id.categoria);
        descricao = view.findViewById(R.id.descricao);
        pontuacao = view.findViewById(R.id.pontuacao);
    }

    public void setClick() {
        finalizaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_home);
            }
        });
    }

    private void setText() {
        categoria.setText(Database.getFeedback().getCategoria());
        descricao.setText(Database.getFeedback().getDescricao());
        pontuacao.setText(Database.getFeedback().getPontuacao());
    }

}