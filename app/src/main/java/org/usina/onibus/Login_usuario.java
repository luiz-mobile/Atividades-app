package org.usina.onibus;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_usuario extends AppCompatActivity {

    Button callSignUp;

    // ProgressBar progressBar;


    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup;
    private Button btnReset;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getWindow ().setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView ( R.layout.activity_login_usuario );

        // Obter instância de autenticação do Firebase
        auth=FirebaseAuth.getInstance ();

        if (auth.getCurrentUser () != null) {
            startActivity ( new Intent ( Login_usuario.this, MainActivity.class ) );
            finish ();
        }

        // defina a vista agora
        setContentView ( R.layout.activity_login_usuario );

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        inputEmail=( EditText ) findViewById ( R.id.email );
        inputPassword=( EditText ) findViewById ( R.id.password );
        progressBar=( ProgressBar ) findViewById ( R.id.progressBar );
        // Button btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin=( Button ) findViewById ( R.id.btn_login );


        btnLogin.setOnClickListener (
                new View.OnClickListener () {
                    @Override
                    public void onClick(View v) {
                        String email=inputEmail.getText ().toString ();
                        final String password=inputPassword.getText ().toString ();

                        if (TextUtils.isEmpty ( email )) {
                            Toast.makeText (
                                    getApplicationContext (),
                                    "Insira o endereço de e-mail!",
                                    Toast.LENGTH_SHORT )
                                    .show ();
                            return;
                        }

                        if (TextUtils.isEmpty ( password )) {
                            Toast.makeText (
                                    getApplicationContext (),
                                    "Digite a senha!",
                                    Toast.LENGTH_SHORT )
                                    .show ();
                            return;
                        }

                        progressBar.setVisibility ( View.VISIBLE );

                        // autenticar usuário
                        auth.signInWithEmailAndPassword ( email, password )
                                .addOnCompleteListener (
                                        Login_usuario.this,
                                        new OnCompleteListener<AuthResult> () {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {

                                                // Se a entrada falhar, exiba uma mensagem para o
                                                // usuário. Se o login for bem-sucedido
                                                // o ouvinte do estado de autenticação será
                                                // notificado e lógico para lidar com o
                                                // o usuário conectado pode ser tratado no ouvinte
                                                progressBar.setVisibility ( View.GONE );
                                                if (!task.isSuccessful ()) {
                                                    // Caso tenha um erro Havia um erro
                                                    if (password.length () < 6) {
                                                        //
                                                        inputPassword.setError (
                                                                getString (
                                                                        R.string.minimum_password ) );
                                                    } else {
                                                        Toast.makeText (
                                                                Login_usuario.this,
                                                                getString (
                                                                        R.string
                                                                                .auth_failed ),
                                                                Toast.LENGTH_LONG )
                                                                .show ();
                                                    }
                                                } else {
                                                    Intent intent=
                                                            new Intent (
                                                                    Login_usuario.this,
                                                                    MainActivity.class );
                                                    startActivity ( intent );
                                                    finish ();
                                                }
                                            }
                                        } );
                    }
                } );

        // botões de recuperar senha e registre-se

        Button cadastro=( Button ) findViewById ( R.id.cadastro ); // chamar o termo
        cadastro.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( Login_usuario.this, Registrar_usuario.class );
                        startActivity ( it );
                    }
                } );

        // chanar aqui recuperar a senha
        Button senha=( Button ) findViewById ( R.id.senha );
        senha.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( Login_usuario.this, Recuperar_senha.class );
                        startActivity ( it );
                    }
                } );



    }


    @RequiresApi(api=Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() { // Botão BACK padrão do android
        ///  startActivity(
        //     new Intent(
        //             this,
        //             AberturaActivity
        //                    .class)); // O efeito ao ser pressionado do botão (no caso abre a
        // activity)
        finishAffinity (); // Método para matar a activity e não deixa-lá indexada na pilhagem
        return;


    }


}