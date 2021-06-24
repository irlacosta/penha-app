package br.edu.ifrs.projetoexemplomd.ui.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.ui.home.HomeViewModel;

import static androidx.databinding.DataBindingUtil.inflate;

public class QuizFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //alterar o style do card no layout fragment_home
        //fazer o card ser clicável e abrir um novo fragment com o item desejada: mapa, jogo, telefones, etc.
        View root = inflate(inflater, R.layout.fragment_quiz, container, false).getRoot();
        //Button btnVoltar = root.findViewById(R.id.button_fragment_voltar);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
