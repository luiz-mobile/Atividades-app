package org.usina.onibus.ui.custom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CustomFragmentViewModel extends ViewModel {


    MutableLiveData<String> mText;

    public CustomFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Custom Fragment");
    }

    LiveData<String> getText(){
        return mText;
    }
}
