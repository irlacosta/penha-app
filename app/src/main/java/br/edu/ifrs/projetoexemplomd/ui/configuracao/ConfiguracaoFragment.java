package br.edu.ifrs.projetoexemplomd.ui.configuracao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.edu.ifrs.projetoexemplomd.R;

public class ConfiguracaoFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {


    public static ConfiguracaoFragment newInstance() {
        return new ConfiguracaoFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragmento_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_configuracao, container, false);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        return root;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_home:
                navController.navigate(R.id.nav_home);
                return true;

            case R.id.bottom_nav_perfil:
                navController.navigate(R.id.nav_perfil);
                return true;
        }
        return true;
    }
}