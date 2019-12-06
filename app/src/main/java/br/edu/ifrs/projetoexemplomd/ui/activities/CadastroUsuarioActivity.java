package br.edu.ifrs.projetoexemplomd.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.edu.ifrs.projetoexemplomd.R;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText edtEmail;
    private EditText edtSenha;
    private EditText edtConfSenha;
    private Button btnCadastrar;
    private ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.txtEmail);
        edtSenha = findViewById(R.id.txtSenha);
        edtConfSenha = findViewById(R.id.txtConfirmarSenha);
        btnCadastrar = findViewById(R.id.btnSalvar);
        loader = findViewById(R.id.cadastrar_usuario_loader);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("") && !edtConfSenha.getText().toString().equals("")) {
                    if (edtSenha.getText().toString().equals(edtSenha.getText().toString())) {
                      loader.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString()).
                                addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(CadastroUsuarioActivity.this, "Cadastro Realizado com Sucesso!", Toast.LENGTH_SHORT).show();
                                        finish(); //volta para a activity anterior - somente para activity
                                        } else {
                                            loader.setVisibility(View.GONE);
                                            Toast.makeText(CadastroUsuarioActivity.this, "Erro ao Efetuar Cadastro", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(CadastroUsuarioActivity.this, "Senha e confirmação de senha devem ser iguais", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(CadastroUsuarioActivity.this, "Informe os dados para o cadastro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
