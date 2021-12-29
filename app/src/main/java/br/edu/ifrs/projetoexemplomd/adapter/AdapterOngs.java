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
import br.edu.ifrs.projetoexemplomd.model.Ong;

public class AdapterOngs extends RecyclerView.Adapter<AdapterOngs.OngViewHolder> {

    private List<Ong> listaOngs = new ArrayList<>();

    public AdapterOngs(List<Ong> listaOngs) {
        this.listaOngs = listaOngs;
    }

    @NonNull
    @Override
    public AdapterOngs.OngViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.fragment_card_ong, viewGroup, false);

        return new OngViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOngs.OngViewHolder ongViewHolder, int i) {
        Ong o = listaOngs.get(i);
        ongViewHolder.nome.setText(o.getNome());
        ongViewHolder.endereco.setText(o.getEndereco());
    }

    @Override
    public int getItemCount() {
        return listaOngs.size();
    }

    public List<Ong> getListaOngs() {
        return listaOngs;
    }

    public void setListaOngs(List<Ong> ongList) {
        this.listaOngs.clear();
        this.listaOngs.addAll(ongList);
        this.notifyDataSetChanged();
    }

    public class OngViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        TextView endereco;

        public OngViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_card_frase_ong);
            endereco = itemView.findViewById(R.id.txt_card_saiba_mais_ong);
        }
    }
}

