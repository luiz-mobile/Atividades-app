package org.usina.onibus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Atualizar_senha extends AppCompatActivity {

    private Button btnChangePassword, btnRemoveUser, changePassword, remove, signOut;
    private TextView email;

    private EditText oldEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_atualizar_senha );

//        getSupportActionBar().hide();


        // obter instância de autenticação do firebase
        auth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.useremail);

        // get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setDataToView(user);

        authListener =
                new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // o estado de autenticação do usuário é alterado - o usuário é nulo
                            // iniciar a atividade de login
                            // botão sair fazer um clique

                            startActivity(new Intent (Atualizar_senha.this, Login_usuario.class));
                            finish();
                        }
                    }
                };

        btnChangePassword = (Button) findViewById(R.id.change_password_button);

        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);

        changePassword = (Button) findViewById(R.id.changePass);

        remove = (Button) findViewById(R.id.remove);
        signOut = (Button) findViewById(R.id.sign_out);

        oldEmail = (EditText) findViewById(R.id.old_email);

        password = (EditText) findViewById(R.id.passwordd);
        newPassword = (EditText) findViewById(R.id.newPassword);

        oldEmail.setVisibility( View.GONE);

        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);

        changePassword.setVisibility(View.GONE);

        remove.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        btnChangePassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oldEmail.setVisibility(View.GONE);

                        password.setVisibility(View.GONE);
                        newPassword.setVisibility(View.VISIBLE);

                        changePassword.setVisibility(View.VISIBLE);

                        remove.setVisibility(View.GONE);
                    }
                });

        Button voltar = (Button) findViewById(R.id.voltar);
        voltar.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent it = new Intent(Atualizar_senha.this, MainActivity.class);
                        startActivity(it);
                    }
                });

        changePassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (user != null && !newPassword.getText().toString().trim().equals("")) {
                            if (newPassword.getText().toString().trim().length() < 6) {
                                newPassword.setError(
                                        "Senha muito curta, insira no mínimo 6 caracteres");
                                progressBar.setVisibility(View.GONE);
                            } else {
                                user.updatePassword(newPassword.getText().toString().trim())
                                        .addOnCompleteListener(
                                                new OnCompleteListener<Void> () {
                                                    @Override
                                                    public void onComplete(
                                                            @NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(
                                                                    Atualizar_senha.this,
                                                                    "Senha é atualizada, entre com nova senha!",
                                                                    Toast.LENGTH_SHORT)
                                                                    .show();
                                                            signOut();
                                                            progressBar.setVisibility(View.GONE);
                                                        } else {
                                                            Toast.makeText(
                                                                    Atualizar_senha.this,
                                                                    "Falha ao atualizar senha!",
                                                                    Toast.LENGTH_SHORT)
                                                                    .show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                            }
                        } else if (newPassword.getText().toString().trim().equals("")) {
                            newPassword.setError("Digite a senha");
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

        btnRemoveUser.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressBar.setVisibility(View.VISIBLE);
                        if (user != null) {
                            user.delete()
                                    .addOnCompleteListener(
                                            new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(
                                                                Atualizar_senha.this,
                                                                "Seu perfil foi excluído.Crie uma conta agora!",
                                                                Toast.LENGTH_SHORT)
                                                                .show();
                                                        startActivity(
                                                                new Intent(
                                                                        Atualizar_senha.this,
                                                                        Login_usuario.class));
                                                        finish();
                                                        progressBar.setVisibility(View.GONE);
                                                    } else {
                                                        Toast.makeText(
                                                                Atualizar_senha.this,
                                                                "Falha ao excluir sua conta!",
                                                                Toast.LENGTH_SHORT)
                                                                .show();
                                                        progressBar.setVisibility(View.GONE);
                                                    }
                                                }
                                            });
                        }
                    }
                });

        signOut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signOut();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {

        // mostrar email do usuário

        email.setText("  " + user.getEmail());
    }

    // this listener will be called when there is change in firebase user session
    FirebaseAuth.AuthStateListener authListener =
            new FirebaseAuth.AuthStateListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        // user auth state is changed - user is null
                        // launch login activity
                        startActivity(new Intent(Atualizar_senha.this, Login_usuario.class));
                        finish();
                    } else {
                        setDataToView(user);
                    }
                }
            };

    // sign out method
    public void signOut() {
        auth.signOut();

        // esse ouvinte será chamado quando houver uma alteração na sessão do usuário do Firebase
        FirebaseAuth.AuthStateListener authListener =
                new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            // user auth state is changed - user is null
                            // launch login activity
                            startActivity(new Intent(Atualizar_senha.this, Login_usuario.class));
                            finish();
                        }
                    }
                };
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }


}



