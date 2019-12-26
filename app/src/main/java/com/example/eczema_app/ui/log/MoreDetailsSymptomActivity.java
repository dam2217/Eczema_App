package com.example.eczema_app.ui.log;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Parcel;
import android.os.Parcelable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ToggleButton;


//import com.example.eczema_app.Eczema;
//import com.example.eczema_app.HerokuService;
import com.example.eczema_app.R;
//import com.example.eczema_app.Service;
//


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoreDetailsSymptomActivity.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoreDetailsSymptomActivity#newInstance} factory method to
 * create an instance of this fragment.
 */

public class MoreDetailsSymptomActivity extends AppCompatActivity {

    LogEntry currentLog = new LogEntry();

    private int yornclickcount = 0;
    private String selectedYorN = "No";
    private String selectedTreatment = "No Treatment Used";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentLog = getIntent().getParcelableExtra("currentLog");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_details_symptoms);
        final Spinner dropDown = findViewById(R.id.whatTreatment);
        String[] treatments = new String[]{"No Treatment Used", "Corticosteroids", "Emollient", "Systematic Therapy", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, treatments);
//        ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(treatments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ToggleButton yorn = findViewById(R.id.yesButton);

        yorn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        yornclickcount = yornclickcount + 1;
                                        if (yornclickcount % 2 == 1) {
                                            selectedYorN = yorn.getText().toString();
                                            currentLog.setTreatmentYorN(selectedYorN);
                                        }
                                        if (yornclickcount % 2 == 0) {
                                            selectedYorN = yorn.getText().toString();
                                            currentLog.setTreatmentYorN(selectedYorN);
                                        }
                                    }
                                });


        dropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedTreatment = dropDown.getSelectedItem().toString();
                currentLog.setTreatmentUsed(selectedTreatment);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });


        dropDown.setAdapter(adapter);

//        getSupportActionBar().setHomeButtonEnabled(true);


    }
}