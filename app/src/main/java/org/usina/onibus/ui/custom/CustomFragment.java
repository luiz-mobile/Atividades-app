package org.usina.onibus.ui.custom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.usina.onibus.R;

public class CustomFragment extends Fragment {


    TextView cText;

    CustomFragmentViewModel customFragmentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        customFragmentViewModel = ViewModelProviders.of(this).get(CustomFragmentViewModel.class);

       View root = inflater.inflate( R.layout.fragment_custom, container,false);
       cText = root.findViewById(R.id.text_custom);

        customFragmentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cText.setText(s);

            }
        });
       return root;
    }
}
