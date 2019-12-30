package com.example.eczema_app.ui.log;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;
import com.example.eczema_app.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        final MultiSelectionSpinner dropDownWhere = findViewById(R.id.whereTreatment);
        final TextView txt = findViewById(R.id.selectTreatment);
        final TextView txt2 = findViewById(R.id.textWhereTreatment);
        txt.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        dropDownWhat.setVisibility(View.GONE);
        dropDownWhere.setVisibility(View.GONE);

        String[] treatments = new String[]{"Select Treatment", "Corticosteroids", "Emollient", "Systemic Therapy", "Other"};

        new weatherTask().execute();
        new pollutionTask().execute();
        new pollenTask().execute();


//      strings of the locations selected on previous page
//        String[] locations = new String[]{}
//      find which of the body parts have been selected - which have values in their string
//        spinner "select all that apply" for those parts
//        will assume that the same treatment has been used for all locations

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


        String[] areas = new String[13];
        final ArrayList<String> affectedAreas = new ArrayList<>();
        areas[0] = String.valueOf(currentLog.getHb());
        areas[1] = String.valueOf(currentLog.getHf());
        areas[2] = String.valueOf(currentLog.getTb());
        areas[3] = String.valueOf(currentLog.getTf());
        areas[4] = String.valueOf(currentLog.getLab());
        areas[5] = String.valueOf(currentLog.getLaf());
        areas[6] = String.valueOf(currentLog.getLlb());
        areas[7] = String.valueOf(currentLog.getLlf());
        areas[8] = String.valueOf(currentLog.getRlb());
        areas[9] = String.valueOf(currentLog.getRlf());
        areas[10] = String.valueOf(currentLog.getRab());
        areas[11] = String.valueOf(currentLog.getRaf());

        int idx = 0;

        String area;
        for (int i = 0; i < 12; i++){
            if (!areas[i].equals("")) {
//                System.out.println("aaaaaaaaaa");
                if (i == 0){
                    area = "Back of Head";
                } else if (i == 1){
                    area = "Front of Head";
                } else if (i == 2){
                    area = "Back of Torso";
                } else if (i == 3){
                    area = "Front of Torso";
                } else if (i == 4){
                    area = "Back of Left Arm";
                } else if (i == 5){
                    area = "Front of Left Arm";
                } else if (i == 6){
                    area = "Back of Left Leg";
                } else if (i == 7){
                    area = "Front of Left Leg";
                } else if (i == 8){
                    area = "Back of Right Leg";
                } else if (i == 9){
                    area = "Front of Right Leg";
                } else if (i == 10){
                    area = "Back of Right Arm";
                } else {
                    area = "Front of Right Arm";
                }
                affectedAreas.add(idx, area);
                idx = idx + 1;
            }
        }


        final String[] affectedAreasStrArray = new String[affectedAreas.size()];
        for (int i = 0; i < affectedAreas.size(); i++){
            affectedAreasStrArray[i] = String.valueOf(affectedAreas.get(i));
        }

//        System.out.println(affectedAreasStrArray.length);
        dropDownWhere.setItems(affectedAreasStrArray);

//      ensure that spinner is only visible when toggle is on 'yes' state
        yesNo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                if (yesNo.isChecked()) {
                    txt.setVisibility(View.VISIBLE);
                    txt2.setVisibility(View.VISIBLE);
                    dropDownWhat.setVisibility(View.VISIBLE);
                    dropDownWhere.setVisibility(View.VISIBLE);
                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                } else {
                    txt.setVisibility(View.GONE);
                    dropDownWhat.setVisibility(View.GONE);
                    txt2.setVisibility(View.GONE);
                    dropDownWhere.setVisibility(View.GONE);
                    dropDownWhat.setAdapter(adapter);
                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                    currentLog.setTreatmentUsed("No Treatment Used");
                    for (int i = 1; i < affectedAreasStrArray.length; i++) {
                        dropDownWhere.resetSelection(i);
                    }
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

                String tempStr = String.valueOf(temp);
                String humidityStr = String.valueOf(humidity);

                currentLog.setHumidity(humidityStr);
                currentLog.setTemperature(tempStr);


            } catch (JSONException e) {
//                System.out.println("No weather info available");
                currentLog.setHumidity("Error");
                currentLog.setTemperature("Error");

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

                String aqi_str = String.valueOf(aqi);

                currentLog.setPollutionLevel(aqi_str);


            } catch (JSONException e) {
                currentLog.setPollutionLevel("Error");
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

                String pollenValueStr = String.valueOf(pollenValue);

                currentLog.setPollenLevel(pollenValueStr);



            } catch (JSONException e) {
                currentLog.setPollenLevel("Error");
            }

        }
    }

//     end of ref
}

