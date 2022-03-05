package org.usina.onibus;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Recuperar_senha extends AppCompatActivity {



    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_recuperar_senha );

        //      getSupportActionBar().hide();
        inputEmail = ( EditText ) findViewById(R.id.emaill);
        btnReset = ( Button ) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        progressBar = ( ProgressBar ) findViewById(R.id.progressBar);


        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

        btnReset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = inputEmail.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(
                                    getApplication(),
                                    "Digite seu ID de e-mail cadastrado",
                                    Toast.LENGTH_SHORT)
                                    .show();
                            return;
                        }

                        progressBar.setVisibility(View.VISIBLE);
                        auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(
                                        new OnCompleteListener<Void> () {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(
                                                            Recuperar_senha.this,
                                                            "Nós lhe enviamos instruções para redefinir sua senha!",
                                                            Toast.LENGTH_SHORT)
                                                            .show();
                                                } else {
                                                    Toast.makeText(
                                                            Recuperar_senha.this,
                                                            "Não foi possível enviar o email de redefinição!",
                                                            Toast.LENGTH_SHORT)
                                                            .show();
                                                }

                                                progressBar.setVisibility(View.GONE);
                                            }
                                        });
                    }
                });
    }
}




