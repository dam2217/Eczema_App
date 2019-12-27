package com.example.eczema_app.ui.log;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Parcel;
import android.os.Parcelable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.widget.ToggleButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.androdocs.httprequest.HttpRequest;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eczema_app.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MoreDetailsSymptomActivity extends AppCompatActivity {

    LogEntry currentLog = new LogEntry();

    private String selectedYorN = "No";
    private String selectedTreatment = "No Treatment Used";

    public static String lat = "51.5074";
    public static String lon = "0.1278";

    public static String API_KEY = "192f3b8d3bcf418c816fcfeb4934475f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentLog = getIntent().getParcelableExtra("currentLog");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_details_symptoms);

        final ToggleButton yesNo = findViewById(R.id.yesButton);
        final Spinner dropDownWhat = findViewById(R.id.whatTreatment);
//        final Spinner dropDownWhere = findViewById(R.id.whereTreatment);
        final TextView txt = findViewById(R.id.selectTreatment);
        txt.setVisibility(View.GONE);
        dropDownWhat.setVisibility(View.GONE);

        String[] treatments = new String[]{"Select Treatment", "Corticosteroids", "Emollient", "Systemic Therapy", "Other"};

        new weatherTask().execute();
        new pollutionTask().execute();
        new pollenTask().execute();

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
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedTreatment = dropDownWhat.getSelectedItem().toString();
                currentLog.setTreatmentUsed(selectedTreatment);
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
                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                } else {
                    txt.setVisibility(View.GONE);
                    dropDownWhat.setVisibility(View.GONE);
                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                }
            }
        });
    }


//    https://www.androdocs.com/java/creating-an-android-weather-app-using-java.html

    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.breezometer.com/weather/v1/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject data = jsonObj.getJSONObject("data");
                JSONObject temperature = data.getJSONObject("temperature");
                int humidity = data.getInt("relative_humidity");
                float temp = (float) temperature.getDouble("value");


                System.out.println(humidity);
                System.out.println(temp);


            } catch (JSONException e) {
            }

        }
    }

    class pollutionTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.breezometer.com/air-quality/v2/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject data = jsonObj.getJSONObject("data");
                JSONObject indexes = data.getJSONObject("indexes");
                JSONObject baqi = indexes.getJSONObject("baqi");
                int aqi = baqi.getInt("aqi");


                System.out.println(aqi);
//                System.out.println(temp);


            } catch (JSONException e) {
            }

        }
    }
    class pollenTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            String response = HttpRequest.excuteGet("https://api.breezometer.com/air-quality/v2/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject data = jsonObj.getJSONObject("data");
                JSONObject types = data.getJSONObject("types");
                JSONObject tree = types.getJSONObject("tree");
                JSONObject index = tree.getJSONObject("index");
                int pollenValue = index.getInt("value");


                System.out.println(pollenValue);
//                System.out.println(temp);


            } catch (JSONException e) {
                System.out.println("No pollen info available");
            }

        }
    }

//     end of ref
}

