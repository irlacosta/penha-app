package br.edu.ifrs.projetoexemplomd.ui.amigo;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import br.edu.ifrs.projetoexemplomd.R;
import br.edu.ifrs.projetoexemplomd.model.Amigo;
import br.edu.ifrs.projetoexemplomd.model.Dica;

import static br.edu.ifrs.projetoexemplomd.util.Util.hideKeyboard;
import static br.edu.ifrs.projetoexemplomd.util.Util.hideKeyboardFrom;

public class CadastrarAmigoFragment extends Fragment {

    private List<Amigo> listAmigos;
    EditText nomeAmigo, telefoneAmigo;
    private NavController navController;

    public List<Amigo> getListAmigos() {
        return listAmigos;
    }

    public void setListAmigos(List<Amigo> listAmigos) {
        this.listAmigos = listAmigos;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_cadastrar_amigo, container, false);
        nomeAmigo = root.findViewById(R.id.txt_fragment_cadastro_nome_amigo);
        telefoneAmigo = root.findViewById(R.id.txt_fragment_cadastro_telefone_amigo);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Button btnCad = root.findViewById(R.id.btn_fragment_cadastro_amigo);
        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastraAmigo();
            }
        });
        final EditText textInputLayout = root.findViewById(R.id.txt_fragment_cadastro_nome_amigo);
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

    private void cadastraAmigo() {
        Amigo amigo = new Amigo(nomeAmigo.getText().toString(), telefoneAmigo.getText().toString());

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("amigos")
                .push()
                .setValue(amigo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Great Success \\o/", Toast.LENGTH_SHORT).show();
                nomeAmigo.setText("");
                telefoneAmigo.setText("");
                //fechar o teclado
                hideKeyboardFrom(getActivity(), getView().getRootView());
                //voltar para a home onde tem os cards
                navController.navigate(R.id.nav_home);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Erro ao cadastrar amigo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
