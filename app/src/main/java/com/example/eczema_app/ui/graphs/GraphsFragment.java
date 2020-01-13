package com.example.eczema_app.ui.graphs;


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

import com.example.eczema_app.R;

public class GraphsFragment extends Fragment {

    private GraphsViewModel graphsViewModel;
    Button btngraphTime, btngraphHumidity, btngraphTemp, btngraphPollen;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graphsViewModel =
                ViewModelProviders.of(this).get(GraphsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_graphs, container, false);
//        final TextView textView = root.findViewById(R.id.text_t);
//        graphsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        //date graph button click with intent
        btngraphTime = root.findViewById(R.id.btngraphTime);
        btngraphTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent I = new Intent(getActivity(),graphTime.class);
                startActivity(I);
            }
        });

        //humidity graph button click with intent
        btngraphHumidity = root.findViewById(R.id.btngraphHumidity);
        btngraphHumidity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent I = new Intent(getActivity(),graphHumidity.class);
                startActivity(I);
            }
        });

        //temperature graph button click with intent
        btngraphTemp = root.findViewById(R.id.btngraphTemp);
        btngraphTemp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent I = new Intent(getActivity(),graphTemp.class);
                startActivity(I);
            }
        });

        //pollen level graph button click with intent
        btngraphPollen = root.findViewById(R.id.btngraphPollen);
        btngraphPollen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent I = new Intent(getActivity(),graphPollen.class);
                startActivity(I);
            }
        });

        return root;
    }
}