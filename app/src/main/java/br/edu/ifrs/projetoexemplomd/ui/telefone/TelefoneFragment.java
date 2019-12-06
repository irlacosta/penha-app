package br.edu.ifrs.projetoexemplomd.ui.telefone;

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
import br.edu.ifrs.projetoexemplomd.ui.home.HomeFragment;
import br.edu.ifrs.projetoexemplomd.ui.perfil.PerfilFragment;

// implements BottomNavigationView.OnNavigationItemSelectedListener()

public class TelefoneFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    //definição da variável que está visível no layout do fragmento
    RecyclerView recyclerView;

    public static TelefoneFragment newInstance() {
        return new TelefoneFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragmento_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_list_telefone, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewTelefone);
        //view que representa os botoes da parte de baixo da tela(bottom navigation) para conseguir acessa-lo
        BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottomNav);
        //seta o listener dos botoes. Quando clicar no botao vai acontecer uma acao
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //configurar o adapter - que formata que o layout de cada item do recycler
        AdapterTelefonesUteis adapterTelefonesUteis = new AdapterTelefonesUteis(Telefone.inicializaLista());
        //o recycler vai mostrar esses dados (myAdapter)
        recyclerView.setAdapter(adapterTelefonesUteis);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL); //vertical - se adapta ao conteúdo
        //GridLayoutManager layoutManager3 = new GridLayoutManager(getContext(), 2);

        //adiciona um separador entre os itens da lista carregados na tela
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        //definindo o layout do rFecycler
        //para os itens ficarem de acordo com o layout escolhido
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //método para quando sofre um click rápido
                //método que recebe a linha do Recycler que sofreu o click
                Toast.makeText(getContext(), "Item pressionado com click: " + Telefone.inicializaLista().get(position).getLocalTelefone(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //método para quando sofre um click longo
                Toast.makeText(getContext(), "Item pressionado com click longo: " + Telefone.inicializaLista().get(position).getLocalTelefone(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
        return root;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        switch (menuItem.getItemId()) {
            case R.id.bottom_nav_home:
                //trocar o fragmento
                navController.navigate(R.id.nav_home);
                return true;

            case R.id.bottom_nav_perfil:
                navController.navigate(R.id.nav_perfil);
                return true;

            case R.id.bottom_nav_config:
                navController.navigate(R.id.nav_configuracao);
                return false;
        }
        return true;
    };
}