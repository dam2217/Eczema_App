package com.example.eczema_app.ui.log;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;
import com.example.eczema_app.MainActivity;
import com.example.eczema_app.R;
import com.example.eczema_app.ui.HttpCommunicate;
import com.example.eczema_app.ui.LogEntrySerial;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MoreDetailsSymptomActivity extends AppCompatActivity {

    LogEntry currentLog = new LogEntry();

    private String selectedYorN = "No";
    private String selectedTreatment = "No Treatment Used";

    public String locServLat = "";
    public String locServLon = "";
    public String lat;
    public String lon;
    public boolean locationFound = false;
    public String city;


    public static String API_KEY = "e495f8ed38cd43febc7730b66034f2cf";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //button for currentLog
        currentLog = getIntent().getParcelableExtra("currentLog");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_more_details_symptoms);

        final ToggleButton yesNo = findViewById(R.id.yesButton);
        final Button save = findViewById(R.id.save);
        final Spinner dropDownWhat = findViewById(R.id.whatTreatment);
        final MultiSelectionSpinner dropDownWhere = findViewById(R.id.whereTreatment);
        final TextView txt = findViewById(R.id.selectTreatment);
        final TextView txt2 = findViewById(R.id.textWhereTreatment);
        final TextView cityText = findViewById(R.id.locFromGPS);
        final EditText cityLocation = findViewById(R.id.location);
        final Button getLoc = findViewById(R.id.findLocation);
        final EditText notes = findViewById(R.id.Notes);

//      initially, the treatment spinners are set to be invisible
        txt.setVisibility(View.GONE);
        txt2.setVisibility(View.GONE);
        dropDownWhat.setVisibility(View.GONE);
        dropDownWhere.setVisibility(View.GONE);

//        string array of treatments
        String[] treatments = new String[]{"Select Treatment", "Corticosteroids", "Emollient", "Systemic Therapy", "Other"};


//        System.out.println(locServLat);
//        System.out.println(locServLon);
//        System.out.println("aaaaaaaaaa");

//        System.out.println(locServLat);
//        System.out.println(locServLon);
//        System.out.println(!locServLat.equals(""));

//      if there IS a location from location services (i.e. they are on), get the city name from the pulled coordinates to display
        if (!locServLat.equals("")) {
            city = getCityName(locServLat, locServLon);
//          show city name as non-editable text
            String dispLocation = "Current Location: " + city;
            cityText.setText(dispLocation);
            cityLocation.setVisibility(View.INVISIBLE);
            getLoc.setVisibility(View.GONE);
        }

//      create a dropdown for the different treatments
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, treatments) {


            //            https://android--code.blogspot.com/2015/08/android-spinner-hint.html
            @Override
            //      The first item in the spinner is disabled, so that it can be used as a 'hint' but not selected by the user
            public boolean isEnabled(int position) {
                return !(position == 0);
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

//      create spinner dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownWhat.setAdapter(adapter);

//        When a treatment is selected, pass this into the currentLog class for the database
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

        // get the areas that the users selected as having inflammation
        final String[] affectedAreas = getAffectedAreas();


//      create the dropdown of the affected areas for selection of where treatment is used
        dropDownWhere.setItems(affectedAreas);


//      ensure that both spinners are only visible when toggle is on 'yes' state
        yesNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (yesNo.isChecked()) {
                    //              setting the treatment spinners to be visible

                    setTreatmentSpinnersToBe(VISIBLE);

                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                } else {
                    //              reset the treatment spinners to 'GONE' if toggle button is set back to 'NO'
                    setTreatmentSpinnersToBe(GONE);
                    dropDownWhat.setAdapter(adapter);
                    selectedYorN = yesNo.getText().toString();
                    currentLog.setTreatmentYorN(selectedYorN);
                    currentLog.setTreatmentUsed("No Treatment Used");

                    for (int i = 1; i <= affectedAreas.length; i++) {
                        dropDownWhere.resetSelection(i);
                    }
                }
            }

            private void setTreatmentSpinnersToBe(int visibility) {
                txt.setVisibility(visibility);
                txt2.setVisibility(visibility);
                dropDownWhat.setVisibility(visibility);
                dropDownWhere.setVisibility(visibility);

            }
        });


        getLoc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //                when the 'Find' button is clicked, if there has been no text input to the 'Enter City' box (i.e. location has been automatically found), set the
                //              latitude and longitude to those obtained from location services
                //

                if (TextUtils.isEmpty(cityLocation.getText().toString())) {
                    lat = locServLat;
                    lon = locServLon;

                    locationFound = true;
                    //              if text is input to the enter city box (i.e. location could not be automatically found), convert the city name to latitude and longitude
                }
                while (!locationFound) {
//                  https://stackoverflow.com/questions/20166328/how-to-get-longitude-latitude-from-the-city-name-android-code

                    String city = cityLocation.getText().toString();
                    List<LatLng> ll = new ArrayList<>();

                    if (!city.equals("")) {

                        ll = getLatAndLon(ll, city);

                        //                    if no coordinates could be found (e.g. spelling error), present a message to the user to re-enter their location
                        if (ll.size() == 0) {
                            final AlertDialog locationError = new AlertDialog.Builder(MoreDetailsSymptomActivity.this, R.style.CustomDialogTheme).create();
                            locationError.setTitle("Error");
                            locationError.setMessage("Location Not Found. Try Again or Check Spelling. Click Outside to Close");
                            locationError.show();
                            break;

//                      if coordinates could be found, set the latitude and longitude values and present the city as non-editable text
                        } else {

                            extractLatAndLon(ll);

                            city = getCityName(lat, lon);
                            String dispLocation = "Current Location: " + city;
                            cityText.setText(dispLocation);
                            cityLocation.setVisibility(View.INVISIBLE);
                            currentLog.setLocation(city);
                            getLoc.setVisibility(View.GONE);
                            locationFound = true;
                        }
                    }

                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View view) {


//              get the weather, pollution and pollen info for the set location
                new weatherTask().execute();
                new pollutionTask().execute();
                new pollenTask().execute();

                String treatedAreas = dropDownWhere.getSelectedItemsAsString();
                setTreatedAreas(treatedAreas);

                String extraInformation = String.valueOf(notes.getText());
                if (extraInformation!=""){
                    currentLog.setNotes(extraInformation);
                }
                else {currentLog.setNotes("N/A");}

//              when save button clicked, return to home page
                if (locationFound) {
                    Log.i("test", "a");
                    new SendData().execute();
                    Log.i("test", "b");


                    Intent home_intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(home_intent);

                }
            }

        });

    }


    public class SendData extends AsyncTask<String,String,String> {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            try {
                // POST Request

                String currentDate = new SimpleDateFormat("dd-MM-yy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                System.out.println(currentDate);

                LogEntrySerial currentLogSerial = new LogEntrySerial(currentDate, currentTime, currentLog.getHf().toString(),
                currentLog.getHb().toString(), currentLog.getTf().toString(), currentLog.getTb().toString(),
                currentLog.getRaf().toString(), currentLog.getRab().toString(), currentLog.getLaf().toString(),
                currentLog.getLab().toString(), currentLog.getRlf().toString(), currentLog.getRlb().toString(),
                currentLog.getLlf().toString(), currentLog.getLlb().toString(),currentLog.getTreatmentYorN(),
                currentLog.getTreatmentUsed(), currentLog.getTemperature(),
                currentLog.getHumidity(), currentLog.getPollutionLevel(),
                currentLog.getPollenLevel(), currentLog.getLocation(),
                currentLog.getHfTreated(), currentLog.getHbTreated(),
                currentLog.getTfTreated(), currentLog.getTbTreated(),
                currentLog.getRafTreated(), currentLog.getRabTreated(),
                currentLog.getLafTreated(), currentLog.getLabTreated(),
                currentLog.getRlfTreated(), currentLog.getRlbTreated(),
                currentLog.getLlfTreated(), currentLog.getLlbTreated(),
                currentLog.getNotes());

                Gson gson = new Gson();
                String jsonString = gson.toJson(currentLogSerial);
                System.out.println("JSON: " + jsonString);
                String message = jsonString;
                byte[] body = message.getBytes(StandardCharsets.UTF_8);

                return HttpCommunicate.sendPost("https://eczema-app.herokuapp.com/eczemadatabase", body);
            } catch (Exception e) {
                Log.i("caught?", "unfortunately");
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if (s != null) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }
    }

    private String[] getAffectedAreas() {
        String[] areas = new String[13];
        ArrayList<String> affectedAreas = new ArrayList<>();
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

//      for the areas with inflammation, convert to a string array for use in a dropdown
        String[] affectedAreasStrArray = new String[affectedAreas.size()];
        for (int i = 0; i < affectedAreas.size(); i++){
            affectedAreasStrArray[i] = String.valueOf(affectedAreas.get(i));
        }
        return affectedAreasStrArray;
    }

    private void setTreatedAreas(String treatedAreas) {

        String[] allTreatedAreas = treatedAreas.split(",");
//              remove the ' ' from the start of the second area onwards that comes when splitting the string
        for (int i = 1; i < allTreatedAreas.length; i++) {
            allTreatedAreas[i] = allTreatedAreas[i].substring(1);
        }

//              for all the areas, if the area was selected as having treatment applied, set treatment use to true
        for (String allTreatedArea : allTreatedAreas) {
            switch (allTreatedArea) {
                case "Back of Head":
                    currentLog.setHbTreated(true);
                    break;
                case "Front of Head":
                    currentLog.setHfTreated(true);
                    break;
                case "Back of Torso":
                    currentLog.setTbTreated(true);
                    break;
                case "Front of Torso":
                    currentLog.setTfTreated(true);
                    break;
                case "Back of Left Arm":
                    currentLog.setLabTreated(true);
                    break;
                case "Front of Left Arm":
                    currentLog.setLafTreated(true);
                    break;
                case "Back of Left Leg":
                    currentLog.setLlbTreated(true);
                    break;
                case "Front of Left Leg":
                    currentLog.setLlfTreated(true);
                    break;
                case "Back of Right Leg":
                    currentLog.setRlbTreated(true);
                    break;
                case "Front of Right Leg":
                    currentLog.setRlfTreated(true);
                    break;
                case "Back of Right Arm":
                    currentLog.setRabTreated(true);
                    break;
                case "Front of Right Arm":
                    currentLog.setRafTreated(true);
                    break;
            }
        }

    }
    //location database matching
    private void extractLatAndLon(List<LatLng> ll) {
        String latLong = String.valueOf(ll.get(0));
        String[] arrOfStr1 = latLong.split("\\(");
        String[] arrOfStr2 = arrOfStr1[1].split(",");
        lat = arrOfStr2[0];
        String[] arrOfStr3 = arrOfStr2[1].split("\\)");
        lon = arrOfStr3[0];
    }

    private List<LatLng> getLatAndLon(List<LatLng> ll, String city) {
        if (Geocoder.isPresent()) {
            try {
                Geocoder gc = new Geocoder(getApplicationContext());
                List<Address> addresses = gc.getFromLocationName(city, 5); // get the found Address Objects

                ll = new ArrayList<>(addresses.size()); // A list to save the coordinates if they are available

                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//                            end ref
        }
        return ll;
    }

//    https://www.androdocs.com/java/creating-an-android-weather-app-using-java.html

    @SuppressLint("StaticFieldLeak")
    class weatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            return HttpRequest.excuteGet("https://api.breezometer.com/weather/v1/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
        }

        @Override
        protected void onPostExecute(String result) {
//          select what metadata from API is required - see documentation on https:api.//breezometer.com to alter
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
                currentLog.setHumidity("Error");
                currentLog.setTemperature("Error");

            }

        }
    }
    //getrequest for jsonobject from database
    @SuppressLint("StaticFieldLeak")
    class pollutionTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            return HttpRequest.excuteGet("https://api.breezometer.com/air-quality/v2/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
        }

        @Override
        protected void onPostExecute(String result) {
//          select what metadata from API is required - see documentation on https:api.//breezometer.com to alter
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

    @SuppressLint("StaticFieldLeak")
    class pollenTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            return HttpRequest.excuteGet("https://api.breezometer.com/air-quality/v2/current-conditions?lat="+ lat + "&lon=" + lon + "&key=" + API_KEY);
        }

        @Override
        protected void onPostExecute(String result) {
//          select what metadata from API is required - see documentation on https:api.//breezometer.com to alter
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

//    function to get the name of the city from the latitude and longitude
    protected String getCityName(String lat, String lon) {
//        https://stackoverflow.com/questions/22323974/how-to-get-city-name-by-latitude-longitude-in-android
        Geocoder geocoder = new Geocoder(MoreDetailsSymptomActivity.this, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(Double.valueOf(lat), Double.valueOf(lon), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert addresses != null;
        String cityName = addresses.get(0).getLocality();
        if (cityName == null){
            cityName = addresses.get(0).getSubAdminArea();
        }
//            end ref
        return cityName;
    }

//     end of ref
}

