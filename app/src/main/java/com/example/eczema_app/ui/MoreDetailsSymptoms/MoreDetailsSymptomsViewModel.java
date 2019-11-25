package com.example.eczema_app.ui.MoreDetailsSymptoms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoreDetailsSymptomsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoreDetailsSymptomsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("More Detailed Symptoms");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
