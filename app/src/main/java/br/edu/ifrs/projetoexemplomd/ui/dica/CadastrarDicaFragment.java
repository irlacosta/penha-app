package br.edu.ifrs.projetoexemplomd.ui.dica;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.model.Dica;

public class CadastrarDicaFragment extends Fragment {

    private List<Dica> listDicas;
    EditText assunto, descricao;
    private FragmentListener listener;


    public List<Dica> getListDicas() {
        return listDicas;
    }

    public void setListDicas(List<Dica> listDicas) {
        this.listDicas = listDicas;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (CadastrarDicaFragment.FragmentListener) context;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cadastrar_dica, container, false);
        assunto = root.findViewById(R.id.txt_fragment_cadastro_assunto_dica);
        descricao = root.findViewById(R.id.txt_fragment_cadastro_descricao_dica);

        Button btnCad = root.findViewById(R.id.btn_fragment_cadastro_dica);
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraDica();
            }
        });
        final EditText textInputLayout = root.findViewById(R.id.txt_fragment_cadastro_descricao_dica);
        textInputLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (focused)
                    keyboard.showSoftInput(textInputLayout, 0);
                else
                    keyboard.hideSoftInputFromWindow(textInputLayout.getWindowToken(), 0);
            }
        });
        return root;
    }

    private void cadastraDica() {
        Dica dica = new Dica(assunto.getText().toString(), descricao.getText().toString());

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("dicas")
                .push()
                .setValue(dica).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Great Success \\o/", Toast.LENGTH_SHORT).show();
                assunto.setText("");
                descricao.setText("");
                listener.voltar();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        });
        ;
    }

    public static interface FragmentListener {
        void voltar();
    }
}