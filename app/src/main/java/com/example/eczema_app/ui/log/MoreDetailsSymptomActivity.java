package com.example.eczema_app.ui.log;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eczema_app.R;

public class MoreDetailsSymptomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_details_symptoms);

        final ToggleButton yesNo = findViewById(R.id.yesButton);

        final Spinner dropDownWhat = findViewById(R.id.whatTreatment);
//        final Spinner dropDownWhere = findViewById(R.id.whereTreatment);
        final TextView txt = findViewById(R.id.selectTreatment);
        txt.setVisibility(View.GONE);
        dropDownWhat.setVisibility(View.GONE);

        String[] treatments = new String[]{"Select Treatment", "Corticosteroids", "Emollient", "Systemic Therapy", "Other"};

//      strings of the locations selected on previous page
//        String[] locations = new String[]{}

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, treatments){


//            https://android--code.blogspot.com/2015/08/android-spinner-hint.html
        @Override
//      The first item in the spinner is disabled, so that it can be used as a 'hint' but not selected by the user
        public boolean isEnabled(int position){
            return !(position == 0);
        }

        @Override
        public View getDropDownView(int position, View convertView,
                ViewGroup parent) {
            View view = super.getDropDownView(position, convertView, parent);
            TextView tv = (TextView) view;
            if(position == 0){
                // Set the hint text color gray
                tv.setTextColor(Color.GRAY);
            }
            else {
                tv.setTextColor(Color.BLACK);
            }
            return view;
        }
    };

//      create spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownWhat.setAdapter(adapter);

        dropDownWhat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItemText = (String) parent.getItemAtPosition(position);
            // If user change the default selection
            // First item is disable and it is used for hint
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

// end of ref

//      ensure that spinner is only visible when toggle is on 'yes' state
        yesNo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (yesNo.isChecked()) {
                    txt.setVisibility(View.VISIBLE);
                    dropDownWhat.setVisibility(View.VISIBLE);
                } else {
                    txt.setVisibility(View.GONE);
                    dropDownWhat.setVisibility(View.GONE);
                }
            }
        });

    }



}