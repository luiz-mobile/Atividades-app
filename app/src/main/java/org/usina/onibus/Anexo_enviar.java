package org.usina.onibus;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Anexo_enviar extends AppCompatActivity {

    // enviar anexo
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_anexo_enviar );


       // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/enviar-anexo/in%C3%ADcio"));
       // startActivity(browserIntent);


    }


}

    /*    WebView webView=( WebView ) findViewById ( R.id.webView );
        webView.setWebViewClient ( new Anexo_enviar.myWebClient () );
        webView.getSettings ().setJavaScriptEnabled ( true );
        webView.loadUrl (

                "https://sites.google.com/view/enviar-anexo/in%C3%ADcio" );




//https://script.google.com/macros/s/AKfycbzZSMc3TxCDGTYcfk1zZjqFw5wCcJqcdMJlwX25uLPZMVN8Mk2TjMcLE_zwvjhkxVirbQ/exec

        webView.setWebViewClient (
                new WebViewClient () {
                    public void onReceivedError(
                            WebView webView, int errorCode, String description, String failingUrl) {
                        try {
                            webView.stopLoading ();
                        } catch (Exception e) {
                        }

                        if (webView.canGoBack ()) {
                            webView.goBack ();
                        }

                        webView.loadUrl ( "about:blank" );
                        AlertDialog alertDialog;
                        alertDialog=new AlertDialog.Builder ( Anexo_enviar.this ).create ();
                        alertDialog.setTitle ( "Erro" );
                        alertDialog.setMessage (
                                "Verifique sua conexão com a Internet e tente novamente." );
                        alertDialog.setButton (
                                DialogInterface.BUTTON_POSITIVE,
                                "Tente novamente",
                                new DialogInterface.OnClickListener () {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish ();
                                        startActivity ( getIntent () );
                                    }
                                } );

                        alertDialog.show ();
                        super.onReceivedError ( webView, errorCode, description, failingUrl );
                    }
                } );


        Button voltar=( Button ) findViewById ( R.id.voltar );
        voltar.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( Anexo_enviar.this, MainActivity.class );
                        startActivity ( it );
                    }
                } );


    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted ( view, url, favicon );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl ( url );
            return true;
        }
    }

}

     */

