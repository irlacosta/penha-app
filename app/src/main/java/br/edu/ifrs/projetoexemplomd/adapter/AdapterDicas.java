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

    public AdapterDicas() { }

    @NonNull
    @Override
    public AdapterDicas.DicaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_card_dica, viewGroup, false); //pode ser usado aqui card ou list

        return new DicaViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDicas.DicaViewHolder dicaViewHolder, int i) {
        Dica d = listaDicas.get(i);
        dicaViewHolder.assunto.setText(d.getAssuntoDica());
        dicaViewHolder.descricao.setText(d.getDescricaoDica());
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
        TextView assunto;
        TextView descricao;

        public DicaViewHolder(View itemView) {
            super(itemView);
            assunto = itemView.findViewById(R.id.txt_item_card_assunto_dica);
            descricao = itemView.findViewById(R.id.txt_item_card_descricao_dica);
        }
    }
}

