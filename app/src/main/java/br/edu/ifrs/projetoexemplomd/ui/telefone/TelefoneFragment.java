package br.edu.ifrs.projetoexemplomd.ui.telefone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterTelefonesUteis;
import br.edu.ifrs.projetoexemplomd.dao.SettingsFirebase;
import br.edu.ifrs.projetoexemplomd.data.Database;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Pergunta;
import br.edu.ifrs.projetoexemplomd.model.Telefone;

// implements BottomNavigationView.OnNavigationItemSelectedListener()

public class TelefoneFragment extends Fragment {
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
        View root = inflater.inflate(R.layout.fragment_telefone, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewTelefone);

        SettingsFirebase.getNo("telefones").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Telefone> telefones = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Telefone telefone = postSnapshot.getValue(Telefone.class);
                    telefones.add(telefone);
                }
                setList(telefones);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });

        return root;
    }

    private void setList(final List<Telefone> telefones) {
        AdapterTelefonesUteis adapterTelefonesUteis = new AdapterTelefonesUteis(telefones);
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
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telefones.get(position).getNumero()));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }
}