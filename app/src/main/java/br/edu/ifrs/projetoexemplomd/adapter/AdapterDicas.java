package br.edu.ifrs.projetoexemplomd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.model.Dica;

public class AdapterDicas extends RecyclerView.Adapter<AdapterDicas.DicaViewHolder> {

    private List<Dica> listaDicas = new ArrayList<>();

    public AdapterDicas(List<Dica> listaDicas) {
        this.listaDicas = listaDicas;
    }

    @NonNull
    @Override
    public AdapterDicas.DicaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.fragment_card_dica, viewGroup, false);

        return new DicaViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDicas.DicaViewHolder dicaViewHolder, int i) {
        Dica d = listaDicas.get(i);
        dicaViewHolder.dicaFrase.setText(d.getFraseDica());
        dicaViewHolder.saibaMais.setText(d.getSaibaMaisDica());
    }

    @Override
    public int getItemCount() {
        return listaDicas.size();
    }

    public List<Dica> getListaDicas() {
        return listaDicas;
    }

    public void setListaDicas(List<Dica> dicaList) {
        this.listaDicas.clear();
        this.listaDicas.addAll(dicaList);
        this.notifyDataSetChanged();
    }

    public class DicaViewHolder extends RecyclerView.ViewHolder {
        TextView dicaFrase;
        TextView saibaMais;

        public DicaViewHolder(View itemView) {
            super(itemView);
            dicaFrase = itemView.findViewById(R.id.txt_card_frase_dica);
            saibaMais = itemView.findViewById(R.id.txt_card_saiba_mais_dica);
        }
    }
}

