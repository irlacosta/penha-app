package br.edu.ifrs.projetoexemplomd.ui.amigo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterAmigos;
import br.edu.ifrs.projetoexemplomd.dao.SettingsFirebase;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Amigo;
import br.edu.ifrs.projetoexemplomd.model.Dica;

public class AmigoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterAmigos adapterAmigos;
    private FloatingActionButton fabAmigo;
    private NavController navController;

    public static AmigoListFragment newInstance() {
        return new AmigoListFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //carrega o fragmento_list e associa com a variável root
        View root = inflater.inflate(R.layout.fragment_list_amigo, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewAmigo);
        fabAmigo = root.findViewById(R.id.fab_amigo);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        onFabClickListener();
        configuraRecycle();
        carregaListAmigos();
        return root;
    }

    public void configuraRecycle() {
        adapterAmigos = new AdapterAmigos();
        recyclerView.setAdapter(adapterAmigos);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
            }

            @Override
            public void onLongItemClick(View view, int position) {
                mostarDialogConfirmacao(adapterAmigos.getListaAmigos().get(position));
            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));
    }

    public void carregaListAmigos() {
        DatabaseReference reference = SettingsFirebase.getNo("amigos");
        final ArrayList<Amigo> listAmigos;
        listAmigos = new ArrayList<>();
        //associar os eventos ao nó produtos para poder buscar os dados
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            //é chamado sempre que consegue recuperar algum dado
            //DataSnapshot é o retorno do Firebase => resultado da consulta
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    //para buscar todos os nós filhos de produtos
                    Amigo amigo = ds.getValue(Amigo.class);
                    amigo.setIdAmigo(ds.getKey());
                    listAmigos.add(amigo);
                }
                //adapter apenas seta nova lista de dados trazidos do banco
                adapterAmigos.setListaDicas(listAmigos);
            }

            @Override
            //chamado quando a requisição é cancelada
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void mostarDialogConfirmacao(final Amigo amigo) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //define o titulo
        builder.setTitle("Atenção");
        //define a mensagem
        builder.setMessage("Tem certeza que deseja excluir " + amigo.getNomeAmigo() + "?");
        //define um botão como positivo
        builder.setPositiveButton("EXCLUIR", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                deletarAmigoNoFirebase(amigo);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("CANCELAR", null);
        //cria e exibe o AlertDialog
        builder.create().show();
    }

    private void deletarAmigoNoFirebase(Amigo amigo) {
        DatabaseReference reference = SettingsFirebase.getNo("amigos").child(amigo.getIdAmigo());

        reference
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Amigo excluído", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Não foi possível excluir", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onFabClickListener() {
        fabAmigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.nav_cadastrar_amigo);
            }
        });
    }
}