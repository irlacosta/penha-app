package br.edu.ifrs.projetoexemplomd.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterTelefonesUteis;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Telefone;

// implements BottomNavigationView.OnNavigationItemSelectedListener()

public class PerfilFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    //definição da variável que está visível no layout do fragmento
    RecyclerView recyclerView;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragmento_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        //view que representa os botoes da parte de baixo da tela(bottom navigation) para conseguir acessa-lo
        BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottomNav);
        //seta o listener dos botoes. Quando clicar no botao vai acontecer uma acao
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

            case R.id.bottom_nav_config:
                navController.navigate(R.id.nav_configuracao);
                return true;
        }

        return true;
    };
}