package br.edu.ifrs.projetoexemplomd.ui.dica;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.adapter.AdapterDicas;
import br.edu.ifrs.projetoexemplomd.listener.RecyclerItemClickListener;
import br.edu.ifrs.projetoexemplomd.model.Telefone;

public class DicaFragment extends Fragment {
    
    private RecyclerView recyclerView;
    private AdapterDicas adapterDicas;
    private NavController navController;

    public static DicaFragment newInstance() {
        return new DicaFragment();
    }

    //objeto não pode estar nulo
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dica, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewDica);
        AdapterDicas adapterDicas = new AdapterDicas();
        recyclerView.setAdapter(adapterDicas);
        recyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        //separador entre os cards
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));

        return root;
    }


//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        WebView myWebView = (WebView) view.findViewById(R.id.webview);
//        myWebView.loadUrl("https://www.google.com");
//    }

}