package com.example.eczema_app.ui.log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoggingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LoggingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Log Eczema");
    }

    public LiveData<String> getText() {
        return mText;
    }
}