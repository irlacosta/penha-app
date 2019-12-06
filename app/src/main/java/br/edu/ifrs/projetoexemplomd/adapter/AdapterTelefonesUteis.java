package br.edu.ifrs.projetoexemplomd.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.model.Telefone;

public class AdapterTelefonesUteis extends RecyclerView.Adapter<AdapterTelefonesUteis.MyViewHolder> {

    List<Telefone> listaTelefones = new ArrayList<>(); //inicializar a lista

    //garantir que a lista será criada fica dentro do contrutor
    public AdapterTelefonesUteis(List<Telefone> telefones) {
        this.listaTelefones = telefones;
        Log.d("teste", telefones.toString());
    }

    @NonNull
    @Override
    public AdapterTelefonesUteis.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //chamado para criar as visualizações - somente as primeiras que aparecem para o usuário
        //convertendo o XML em uma visualização
        //cria um objeto do tipo view
        View itemList = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_list_telefone, viewGroup, false);//posso usar o card aqui
        //se colocar adapter_list_dica usa a lista com separador ou sem separador
        //o separador fica na classe AmigoListFragment
        //retorna o itemList que é passado para o construtor da MyViewHolder
        return new MyViewHolder(itemList);//processa enquanto houverem dados na lista até o fim
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTelefonesUteis.MyViewHolder myViewHolder, int i) {
        //exibe os itens no Recycler
        Telefone t = listaTelefones.get(i);
        myViewHolder.localTelefone.setText(t.getLocalTelefone());
        myViewHolder.numeroTelefone.setText(String.valueOf(t.getNumeroTelefone()));
    }

    @Override
    //quantos itens irá mostrar na tela - semelhante ao Ajax, feed de notícias
    public int getItemCount() {
        //retorna a quantidade de itens que será exibida
        return listaTelefones.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{ //classe interna do adaptador
        //telefone que serão exibidos no recycler
        TextView localTelefone;
        TextView numeroTelefone;

        public MyViewHolder(View itemView){
            super(itemView);
            //passa uma referência para os componentes que estão na interface
            localTelefone = itemView.findViewById(R.id.txt_adapter_list_local_telefone);
            numeroTelefone = itemView.findViewById(R.id.txt_adapter_list_numero_telefone);
        }
    }
}
