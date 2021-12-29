package br.edu.ifrs.projetoexemplomd.ui.ong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterDicas;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterOngs;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterTelefonesUteis;
import br.edu.ifrs.projetoexemplomd.dao.SettingsFirebase;
import br.edu.ifrs.projetoexemplomd.data.Database;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Dica;
import br.edu.ifrs.projetoexemplomd.model.Ong;
import br.edu.ifrs.projetoexemplomd.model.Telefone;
import br.edu.ifrs.projetoexemplomd.ui.dica.DicaFragment;

// implements BottomNavigationView.OnNavigationItemSelectedListener()

public class OngFragment extends Fragment {


    public static OngFragment newInstance() {
        return new OngFragment();
    }

    RecyclerView recyclerView;
    private NavController navController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ong, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewOng);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        SettingsFirebase.getNo("ongs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Ong> ongs = new ArrayList<>();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ong ong = postSnapshot.getValue(Ong.class);
                    ongs.add(ong);
                }
                setList(ongs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                databaseError.getMessage();
            }
        });

        return root;
    }

    private void setList(final List<Ong> ongs) {
        AdapterOngs adapterOngs = new AdapterOngs(ongs);
        //o recycler vai mostrar esses dados (myAdapter)
        recyclerView.setAdapter(adapterOngs);
        //linha de código usada para otimizar o recycler
        recyclerView.setHasFixedSize(true);

        //configurar o gerenciador de layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Database.setNavegadorUrl(ongs.get(position).getUrl());
                navController.navigate(R.id.nav_navegador);
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