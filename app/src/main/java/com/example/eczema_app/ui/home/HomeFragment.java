package com.example.eczema_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eczema_app.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView noLocations1 = root.findViewById(R.id.no_locations_1);
        final TextView treatment1 = root.findViewById(R.id.treatment_1);
        final TextView date1 = root.findViewById((R.id.date_1));
        noLocations1.setText("Locations: 3");
        final Button notes1 = root.findViewById(R.id.notes_1);

        final TextView noLocations2 = root.findViewById(R.id.no_locations_2);
        final TextView treatment2 = root.findViewById(R.id.treatment_2);
        final TextView date2 = root.findViewById((R.id.date_2));
        final Button notes2 = root.findViewById(R.id.notes_2);

        final TextView noLocations3 = root.findViewById(R.id.no_locations_3);
        final TextView treatment3 = root.findViewById(R.id.treatment_3);
        final TextView date3 = root.findViewById((R.id.date_3));
        final Button notes3 = root.findViewById(R.id.notes_3);

        final TextView noLocations4 = root.findViewById(R.id.no_locations_4);
        final TextView treatment4 = root.findViewById(R.id.treatment_4);
        final TextView date4 = root.findViewById((R.id.date_4));
        final Button notes4 = root.findViewById(R.id.notes_4);

//        check number of entries, if less than 4 set additional cards to invisible

//        check and set number of locations
//        get the affected locations and put strings as list in popup
//        get the treatment used
//        if no notes, set button to 'gone', else set textview to gone
//        get the locations on which treatment was used and put strings as list in popup

        //creating activity of AlertDialog
        noLocations1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
                inflammationLocations.setMessage("Insert list of locations and severity for most recent entry ");
                inflammationLocations.show();
            }
        });

        treatment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Treatment: ....");
                inflammationLocations.setMessage("List of treated areas for most recent entry");
                inflammationLocations.show();
            }
        });

        notes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Notes");
                inflammationLocations.setMessage("notes for most recent entry");
                inflammationLocations.show();
            }
        });

        noLocations2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
                inflammationLocations.setMessage("Insert list of locations and severity for second entry ");
                inflammationLocations.show();
            }
        });

        treatment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Treatment: ....");
                inflammationLocations.setMessage("List of treated areas for second entry");
                inflammationLocations.show();
            }
        });

        notes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Notes");
                inflammationLocations.setMessage("notes for  second entry");
                inflammationLocations.show();
            }
        });

        noLocations3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
                inflammationLocations.setMessage("Insert list of locations and severity for third entry");
                inflammationLocations.show();
            }
        });

        treatment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Treatment: ....");
                inflammationLocations.setMessage("List of treated areas for third entry");
                inflammationLocations.show();
            }
        });

        notes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Notes");
                inflammationLocations.setMessage("notes for third entry");
                inflammationLocations.show();
            }
        });

        noLocations4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Inflammations");
                inflammationLocations.setMessage("Insert list of locations and severity for 4th entry");
                inflammationLocations.show();
            }
        });

        treatment4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Treatment: ....");
                inflammationLocations.setMessage("List of treated areas of 4th entry");
                inflammationLocations.show();
            }
        });

        notes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog inflammationLocations = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTheme).create();
                inflammationLocations.setTitle("Notes");
                inflammationLocations.setMessage("notes of 4th entry");
                inflammationLocations.show();
            }
        });
        return root;
    }

}