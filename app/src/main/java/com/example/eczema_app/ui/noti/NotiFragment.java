package com.example.eczema_app.ui.noti;

import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eczema_app.R;

public class NotiFragment extends Fragment {

    private NotiViewModel mViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(NotiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications,container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        mViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
