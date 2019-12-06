package br.edu.ifrs.projetoexemplomd.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import br.edu.ifrs.projetoexemplomd.R;

import static androidx.databinding.DataBindingUtil.inflate;

public class HomeFragment extends Fragment { //implements View.OnClickListener{ //está dizendo eu sou um fragmento

    private HomeViewModel homeViewModel;
    private CardView card_amigos;
    private CardView card_quiz;
    private CardView card_dicas;
    private CardView card_telefones;
    private CardView card_mapa;
    private NavController navController;

    //método que caracteriza o fragmento onCreateView
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //alterar o style do card no layout fragment_home
        //fazer o card ser clicável e abrir um novo fragment com o item desejada: mapa, jogo, telefones, etc.
        View root = inflate(inflater, R.layout.fragment_home, container, false).getRoot();
        //Button btnVoltar = root.findViewById(R.id.button_fragment_voltar);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        setClick();
    }

    private void bind(View view) {
        card_amigos = view.findViewById(R.id.card_one);
        card_quiz = view.findViewById(R.id.card_two);
        card_dicas = view.findViewById(R.id.card_three);
        card_telefones = view.findViewById(R.id.card_four);
        card_mapa = view.findViewById(R.id.card_five);
    }

    private void setClick() {
        card_amigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               navController.navigate(R.id.nav_listar_amigos);
            }
        });
        card_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navegar quando tiver a tela
                Toast.makeText(getContext(), "Clicou em quiz", Toast.LENGTH_SHORT).show();
            }
        });
        card_dicas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_ver_dicas);
            }
        });
        card_telefones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_phone);
            }
        });
        card_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0? q=-30.0264276,-51.2233058(IFRS POA)?z=15"));
                    startActivity(intent);
            }
        });
    }
}