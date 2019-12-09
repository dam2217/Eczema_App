package com.example.eczema_app.ui.log;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_details_symptoms);
        Spinner dropDown = findViewById(R.id.whatTreatment);
        String[] treatments = new String[]{"No Treatment Used", "Corticosteroids", "Emollient", "Systematic Therapy", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, treatments);
//        ArrayAdapter<String> adapter = ArrayAdapter.createFromResource(treatments, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dropDown.setAdapter(adapter);

    }
}