package org.usina.onibus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 // Use the {@link Solicitar_cadastrokm newInstance} factory method to
 * create an instance of this fragment.
 */
public class Solicitar_cadastrokm extends Fragment {

    // private WebView solicitar cadastro para enviar informações dakm;
    private WebView webView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.fragment_solicitar_cadastrokm, container, false );

    }


}


