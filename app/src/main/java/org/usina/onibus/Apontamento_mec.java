package org.usina.onibus;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Apontamento_mec extends AppCompatActivity {




    // private WebView apontamento mec;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apontamento_mec);



        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new Apontamento_mec.myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(
                "https://sites.google.com/view/mecanizao-usj-teste/in%C3%ADcio");



        webView.setWebViewClient(
                new WebViewClient() {
                    public void onReceivedError(
                            WebView webView, int errorCode, String description, String failingUrl) {
                        try {
                            webView.stopLoading();
                        } catch (Exception e) {
                        }

                        if (webView.canGoBack()) {
                            webView.goBack();
                        }

                        webView.loadUrl("about:blank");
                        AlertDialog alertDialog;
                        alertDialog = new AlertDialog.Builder(Apontamento_mec.this).create();
                        alertDialog.setTitle("Erro");
                        alertDialog.setMessage(
                                "Verifique sua conexão com a Internet e tente novamente.");
                        alertDialog.setButton(
                                DialogInterface.BUTTON_POSITIVE,
                                "Tente novamente",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                });

                        alertDialog.show();
                        super.onReceivedError(webView, errorCode, description, failingUrl);
                    }
                } );


        Button voltar =( Button ) findViewById ( R.id.voltar );
        voltar.setOnClickListener (
                new View.OnClickListener () {
                    public void onClick(View v) {
                        Intent it=new Intent ( Apontamento_mec.this, MainActivity.class );
                        startActivity ( it );
                    }
                } );



    }

    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
    }
}




