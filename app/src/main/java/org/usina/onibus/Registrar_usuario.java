package org.usina.onibus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
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

public class Registrar_usuario extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar2;
    private FirebaseAuth auth;

    @SuppressLint("SourceLockedOrientationActivity")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_registrar_usuario );


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // btnSignIn = ( Button ) findViewById(R.id.sign_in_button);
        btnSignUp = ( Button ) findViewById(R.id.sign_up_button);
        inputEmail = ( EditText ) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar2 = ( ProgressBar ) findViewById(R.id.progressBar2);
        //   btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

        //  btnResetPassword.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         startActivity(new Intent (Cadastro_usuario.this, Usuario_conectado.class));
        //     }
        //  });

        //   btnSignIn.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //      public void onClick(View v) {
        //         finish();
        //    }
        //  });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Insira o endereço de e-mail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Digite a senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Senha muito curta, insira no mínimo 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Registrar_usuario.this, new OnCompleteListener<AuthResult> () {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(Registrar_usuario.this, "criar usuário com email:em completo:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar2.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Registrar_usuario.this, "autenticação falhou." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent (Registrar_usuario.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar2.setVisibility(View.GONE);
    }
}



