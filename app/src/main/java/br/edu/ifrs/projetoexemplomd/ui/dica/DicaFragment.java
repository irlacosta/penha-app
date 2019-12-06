package br.edu.ifrs.projetoexemplomd.ui.dica;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterDicas;
import br.edu.ifrs.projetoexemplomd.dao.SettingsFirebase;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Dica;
import br.edu.ifrs.projetoexemplomd.ui.home.HomeFragment;

public class DicaFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {
    //definição da variável que está visível no layout do fragmento
    private RecyclerView recyclerView;
    private AdapterDicas adapterDicas;
    private FloatingActionButton fabDica;
    private NavController navController;

    public static DicaFragment newInstance() {
        return new DicaFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragmento_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_list_dica, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewDica);
        //configurar o adapter - que formata que o layout de cada item do recycler

        //view que representa os botoes da parte de baixo da tela(bottom navigation) para conseguir acessa-lo
        BottomNavigationView bottomNavigationView = (BottomNavigationView) root.findViewById(R.id.bottomNav);
        //seta o listener dos botoes. Quando clicar no botao vai acontecer uma acao
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        configuraRecycle();
        carregaListDica();
        return root;
    }

    public void configuraRecycle() {
        adapterDicas = new AdapterDicas();
        recyclerView.setAdapter(adapterDicas);
        //recyclerView.setHasFixedSize(true);
        //configurar o gerenciador de layout - podem ser tres tipos (os outros dois estao comentados
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL); //vertical - se adapta ao conteúdo
        //GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        //separador entre os itens
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(getContext(), "Item pressionado com click: " + Dica.inicializaListaDicas().get(position).getAssuntoDica(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                mostarDialogConfirmacao(adapterDicas.getListaDicas().get(position));
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    public void carregaListDica() {
        DatabaseReference reference = SettingsFirebase.getNo("dicas");
        final ArrayList<Dica> listDicas;
        listDicas = new ArrayList<>();
        //associar os eventos ao nó produtos para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Dica> listDicas = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de dicas
                    Dica dica = ds.getValue(Dica.class);
                    dica.setId(ds.getKey());
                    listDicas.add(dica);
                }
                adapterDicas.setListaDicas(listDicas);
            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void mostarDialogConfirmacao(final Dica dica) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        builder.setTitle("Atenção");
        //define a mensagem
        builder.setMessage("Tem certeza que deseja excluir a dica " + dica.getAssuntoDica() + "?");
        //define um botão como positivo
        builder.setPositiveButton("EXCLUIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                deletarDicaNoFirebase(dica);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("CANCELAR", null);
        //cria e exibe o AlertDialog
        builder.create().show();
    }

    private void deletarDicaNoFirebase(Dica dica) {
        DatabaseReference reference = SettingsFirebase.getNo("dicas").child(dica.getId());

        reference
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Dica excluida com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Não foi possível excluir esta dica!", Toast.LENGTH_SHORT).show();
            }
        });
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