package com.example.eczema_app.ui.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eczema_app.R;

import java.util.ArrayList;

public class LogFragment extends Fragment {

    private LoggingViewModel loggingViewModel;

    int headclickcount = 0;
    int torsoclickcount = 0;
    int rarmclickcount = 0;
    int larmclickcount = 0;
    int rlegclickcount = 0;
    int llegclickcount = 0;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loggingViewModel = ViewModelProviders.of(this).get(LoggingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_new_log, container, false);
        final TextView textView = root.findViewById(R.id.NewLogTitle);
        loggingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        ImageButton head = root.findViewById(R.id.head);
        ImageButton torso = root.findViewById(R.id.torso);
        ImageButton rarm = root.findViewById(R.id.rightarm);
        ImageButton larm = root.findViewById(R.id.leftarm);
        ImageButton rleg = root.findViewById(R.id.rightleg);
        ImageButton lleg = root.findViewById(R.id.leftleg);

//        bodyPartsList = new ArrayList<ImageButton>();
//        bodyPartsList.add(head);
//        bodyPartsList.add(torso);
//        bodyPartsList.add(rarm);
//        bodyPartsList.add(larm);
//        bodyPartsList.add(rleg);
//        bodyPartsList.add(lleg);


        final TextView headseverity = root.findViewById(R.id.headSeverity);
        final TextView torsoseverity = root.findViewById(R.id.torsoSeverity);
        final TextView rarmseverity = root.findViewById(R.id.rarmSeverity);
        final TextView larmseverity = root.findViewById(R.id.larmSeverity);
        final TextView rlegseverity = root.findViewById(R.id.rlegSeverity);
        final TextView llegseverity = root.findViewById(R.id.llegSeverity);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headclickcount = headclickcount + 1;
                if(headclickcount%4==1)
                {
                    headseverity.setText("Mild");
                }
                if(headclickcount%4==2)
                {
                    headseverity.setText("Moderate");
                }
                if(headclickcount%4==3)
                {
                    headseverity.setText("Severe");
                }
                if(headclickcount%4==0)
                {
                    headseverity.setText("");
                }
            }
        });

        torso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                torsoclickcount = torsoclickcount + 1;
                if(torsoclickcount%4==1)
                {
                    torsoseverity.setText("Mild");
                }
                if(torsoclickcount%4==2)
                {
                    torsoseverity.setText("Moderate");
                }
                if(torsoclickcount%4==3)
                {
                    torsoseverity.setText("Severe");
                }
                if(torsoclickcount%4==0)
                {
                    torsoseverity.setText("");
                }
            }
        });

        rarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rarmclickcount = rarmclickcount + 1;
                if(rarmclickcount%4==1)
                {
                    rarmseverity.setText("Mild");
                }
                if(rarmclickcount%4==2)
                {
                    rarmseverity.setText("Moderate");
                }
                if(rarmclickcount%4==3)
                {
                    rarmseverity.setText("Severe");
                }
                if(rarmclickcount%4==0)
                {
                    rarmseverity.setText("");
                }
            }
        });

        larm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                larmclickcount = larmclickcount + 1;
                if(larmclickcount%4==1)
                {
                    larmseverity.setText("Mild");
                }
                if(larmclickcount%4==2)
                {
                    larmseverity.setText("Moderate");
                }
                if(larmclickcount%4==3)
                {
                    larmseverity.setText("Severe");
                }
                if(larmclickcount%4==0)
                {
                    larmseverity.setText("");
                }
            }
        });

        rleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlegclickcount = rlegclickcount + 1;
                if(rlegclickcount%4==1)
                {
                    rlegseverity.setText("Mild");
                }
                if(rlegclickcount%4==2)
                {
                    rlegseverity.setText("Moderate");
                }
                if(rlegclickcount%4==3)
                {
                    rlegseverity.setText("Severe");
                }
                if(rlegclickcount%4==0)
                {
                    rlegseverity.setText("");
                }
            }
        });

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llegclickcount = llegclickcount + 1;
                if(llegclickcount%4==1)
                {
                    llegseverity.setText("Mild");
                }
                if(llegclickcount%4==2)
                {
                    headseverity.setText("Moderate");
                }
                if(llegclickcount%4==3)
                {
                    headseverity.setText("Severe");
                }
                if(llegclickcount%4==0)
                {
                    headseverity.setText("");
                }
            }
        });

        return root;
    }



}