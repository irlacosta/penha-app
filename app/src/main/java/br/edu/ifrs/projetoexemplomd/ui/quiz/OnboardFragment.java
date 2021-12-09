package br.edu.ifrs.projetoexemplomd.ui.quiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.data.Database;

public class OnboardFragment extends Fragment {

    private Button onboardBtn;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind(view);
        setClick();
    }

    public void bind(View view) {
        onboardBtn = view.findViewById(R.id.btn_onboard);
    }

    public void setClick() {
        onboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.voltarPergunta();
                navController.navigate(R.id.nav_quiz);
            }
        });
    }

}