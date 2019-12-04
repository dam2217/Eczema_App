package com.example.eczema_app.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eczema_app.NotiActivity;
import com.example.eczema_app.R;

public class SettingsFragment extends Fragment {

    private Button not_button;

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel= ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        not_button = root.findViewById(R.id.button_notifications);
        not_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotificationsPage();
            }
        });
        return root;
    }

    public void openNotificationsPage(){
        Intent intent = new Intent(this, NotiActivity.class);
        startActivity(intent);
    }
}