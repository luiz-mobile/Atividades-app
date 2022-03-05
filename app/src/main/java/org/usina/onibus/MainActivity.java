package org.usina.onibus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private TextView email;
    private FirebaseAuth auth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        Button f1 = (Button) findViewById(R.id.btnFrag1);
        Button f2 = (Button) findViewById(R.id.btnFrag2);

        email=( TextView ) findViewById ( R.id.useremail );
        auth=FirebaseAuth.getInstance ();
        email=( TextView ) findViewById ( R.id.useremail );

        //get current user
        final FirebaseUser user=FirebaseAuth.getInstance ().getCurrentUser ();
        setDataToView ( user );


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "  ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (savedInstanceState == null) {
            // adicionar o fragmento inicial   // mapa todas as fazendas
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_container, new MapsUSJ ())
                    .commit();
        }

        //  campo a
        f1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new Campo_a_Fragment ())
                                .commit();
                    }
                });

        // campo b
        f2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.frame_container, new Campo_b_Fragment ())
                                .commit();
                    }
                });




        // chamar apontamento de atividaes
        ImageView apontamento=( ImageView ) findViewById ( R.id.apontamento );
        apontamento.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Apontamento_atividades.class );
                        startActivity ( it );
                    }
                } );

        // chamar apontamento  de apontamento km
        ImageView consulta=( ImageView ) findViewById ( R.id.consulta );
        consulta.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Apontamento_onibus.class );
                        startActivity ( it );
                    }
                } );


        // chamar apontamento diaria onibus
        ImageView onibusdiaria=( ImageView ) findViewById ( R.id.mec);
        onibusdiaria.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Apontamento_diaria.class );
                        startActivity ( it );
                    }
                } );


        // chamar consultar  diaria onibus
        ImageView consultdiaria=( ImageView ) findViewById ( R.id.fiscais);
        consultdiaria.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Apontamento_onibus.class );
                        startActivity ( it );
                    }
                } );

        // chamar mecanização
        ImageView mec=( ImageView ) findViewById ( R.id.mec );
        mec.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Apontamento_mec.class );
                        startActivity ( it );
                    }
                } );



        // chamar fiscais
        ImageView fiscais=( ImageView ) findViewById ( R.id.fiscais );
        fiscais.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Fiscais.class );
                        startActivity ( it );
                    }
                } );





        // chamar fiscais
        ImageView anexo=( ImageView ) findViewById ( R.id.anexo );
        anexo.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( MainActivity.this, Tecnico_agricola.class );
                        startActivity ( it );
                    }
                } );







        // chamar anexo
     //   ImageView anexo=( ImageView ) findViewById ( R.id.anexo );
      //  anexo.setOnClickListener (
      //          new View.OnClickListener () {
      //              public void onClick(View v) {
      //                  Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/enviar-anexo/in%C3%ADcio"));
       //                 startActivity(browserIntent);
       //             }
       //         } );




       // chamando menu lateral

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_custom,R.id.sairFragment, R.id.nav_activity)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();

                switch (id){
                    case R.id.nav_home:  // inicio
                       // Toast.makeText(MainActivity.this," ",Toast.LENGTH_LONG).show();
                        break;

                        case R.id.nav_gallery: // sobre
                            Intent it=new Intent ( MainActivity.this,  Solicitar_cadastro.class );
                            startActivity ( it );
                        break;

                        case R.id.nav_slideshow: // ir para solicitar dastro km activity
                            it=new Intent ( MainActivity.this, Solicitar_cadastokm.class );
                            startActivity ( it );
                        break;


                        case R.id.nav_custom: // atualizar senha
                        it=new Intent ( MainActivity.this, Atualizar_senha.class );
                        startActivity ( it );
                        break;

                       case R.id.nav_activity: ///ecluir conta
                        it=new Intent ( MainActivity.this, Main2Activity.class );
                        startActivity ( it );
                        break;

                       case R.id.sairFragment: // sair
                        it=new Intent ( MainActivity.this, Sair.class );
                        startActivity ( it );
                        break;
                }

            }
        });
    }

    // mostra email
    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {

        email.setText ( "E-mail: " + user.getEmail () );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId ();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i=new Intent ( MainActivity.this, MainActivity.class );
            startActivity ( i );

        }

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settingss) {

       //     Intent i=new Intent (  MainActivity.this, Sair.class );
        //    startActivity ( i );
       // }


        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settingsss) {

            Intent i=new Intent ( Logado.this, Excluir_conta.class );
            startActivity ( i );

        }


       */

        //  return true;

        return super.onOptionsItemSelected ( item );
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
