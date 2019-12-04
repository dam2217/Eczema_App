package com.example.eczema_app.ui.noti;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotiViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public NotiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Notifications");
    }
    public  LiveData<String> getText() {return mText;}
}
