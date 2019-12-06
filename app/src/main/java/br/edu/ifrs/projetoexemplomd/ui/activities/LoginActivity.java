package br.edu.ifrs.projetoexemplomd.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.edu.ifrs.projetoexemplomd.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private TextInputLayout layoutEmail;
    private TextInputEditText txtEmail;
    private TextInputLayout layoutSenha;
    private TextInputEditText txtSenha;
    private Button btnLogar;
    private Button btnVisitar;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.login_edit_email);
        txtSenha = findViewById(R.id.login_edit_senha);
        btnLogar = findViewById(R.id.btn_login_acessar);
        btnVisitar = findViewById(R.id.btnVisitar);
        btnCadastrar = findViewById(R.id.btn_login_cadastrar_usuario);
        layoutEmail = findViewById(R.id.login_edit_layout_email);
        layoutSenha = findViewById(R.id.login_edit_layout_senha);
        mAuth = FirebaseAuth.getInstance();

        btnVisitar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    //para validar usuários já existentes
                    mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(), txtSenha.getText().toString()) //pega o que o usuário informa no campo
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("LOGIN", "signInWithEmail:success");
                                        currentUser = mAuth.getCurrentUser(); //alterei aqui após copiar código ao lado - Firebase (aba)
                                        txtSenha.setText("");
                                        Log.d("LOGIN", currentUser.getEmail());
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("LOGIN ERRO", "signInWithEmail:failure", task.getException());
                                        Log.w("LOGIN ERRO", txtEmail.getText().toString() + "-" + txtSenha.getText().toString());
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
                else{
                    Snackbar snackbar = Snackbar.make(view, "Login incorreto", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }
    private boolean validarCampos(){
        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Informe o seu e-mail");
            return false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(txtSenha.getText().toString().isEmpty()){
            layoutSenha.setErrorEnabled(true);
            layoutSenha.setError("Informe a sua senha");
            return false;
        }else{
            layoutSenha.setErrorEnabled(false);
        }
        Log.d("Validando Login", "saiu no validar");
        return true;
    }
    @Override
    public void onStart() {
        super.onStart();
        if (currentUser != null)
            Log.d("LOGIN entrou no sistema", currentUser.getEmail());
        else
            Log.d("ERRO", "usuario nulo");
    }

}
