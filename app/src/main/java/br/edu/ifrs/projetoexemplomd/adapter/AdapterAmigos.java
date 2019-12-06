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
import br.edu.ifrs.projetoexemplomd.model.Amigo;

public class AdapterAmigos extends RecyclerView.Adapter<AdapterAmigos.AmigoViewHolder> { //dentro de uma classe ha uma outra classe MyAdapter.MyViewHolder
    private List<Amigo> listaAmigos = new ArrayList<>(); //inicializar a lista

    public AdapterAmigos() {}

    @NonNull
    @Override
    public AdapterAmigos.AmigoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_card_amigo, viewGroup, false); //troquei o adapter_card (era os cards)
        //se colocar adapter_list_dica usa a lista com separador ou sem separador
        //o separador fica na classe AmigoListFragment
        //retorna o itemList que é passado para o construtor da ViewHolder
        return new AmigoViewHolder(itemList);//processa enquanto houverem dados na lista de pessoas até o fim
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAmigos.AmigoViewHolder amigoViewHolder, int i) {
        //exibe os itens no Recycler
        Amigo a = listaAmigos.get(i);
        amigoViewHolder.nomeAmigo.setText(a.getNomeAmigo());
        amigoViewHolder.telefoneAmigo.setText(a.getTelefoneAmigo());
    }

    @Override
    //quantos itens irá mostrar na tela - semelhante ao Ajax, feed de notícias
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaAmigos.size();
    }
    public List<Amigo> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaDicas(List<Amigo> amigosList) {
        this.listaAmigos.clear();
        this.listaAmigos.addAll(amigosList);
        this.notifyDataSetChanged();
    }
    public class AmigoViewHolder extends RecyclerView.ViewHolder{ //classe interna do adaptador
        //dados da pessoa que serão exibidos no recycler
        TextView nomeAmigo;
        TextView telefoneAmigo;

        public AmigoViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            nomeAmigo = itemView.findViewById(R.id.txt_item_card_nome_amigo);
            telefoneAmigo = itemView.findViewById(R.id.txt_item_card_telefone_amigo);
        }
    }
}
