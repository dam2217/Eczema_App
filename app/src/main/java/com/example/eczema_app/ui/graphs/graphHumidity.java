package com.example.eczema_app.ui.graphs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.eczema_app.ui.HttpCommunicate;
import com.example.eczema_app.ui.LogEntrySerial;
import com.example.eczema_app.ui.home.LoggedDataEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import com.example.eczema_app.R;
import com.google.gson.Gson;

public class graphHumidity extends AppCompatActivity {
    private LineChart lineChart;

    String data = "";
    ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_humidity);

        new ReceiveData().execute();

        lineChart = (LineChart) findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(getData(), "Severity levels");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        //enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        //if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);


        //setting x-axis humidity
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] dates = new String[]{"0%", "20%", "40%", "60%", "80%", "100%"};
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return dates[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        //y-axis code
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();

        //setting y-axis
        final String[] severity = new String[]{"Mild", "Moderate", "Severe"};
        ValueFormatter formatter1 = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return severity[(int) value];
            }
        };
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setValueFormatter(formatter1);

        //setting limits of y-axis to be 3 severity levels
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(36);

        //plotting line chart
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();
        lineDataSet.setLineWidth(7);
    }

    private ArrayList getData() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < logList.size(); i++) {
            entries.add(new Entry(Integer.parseInt(logList.get(i).humidity), logList.get(i).severityScore));
        }
        return entries;
    }

    public class ReceiveData extends AsyncTask<String, String, String> {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.i("data received?", "yes");
                data = HttpCommunicate.sendGet("https://eczema-app.herokuapp.com/eczemadatabase");
                System.out.println("data: " + data);

                String[] splits = data.split("split");

                for (int i = 0; i < splits.length; i++) {
                    String individualReceived = "{" + splits[i] + "}";
                    Gson gson = new Gson();
                    LogEntrySerial LogFromDBserial = gson.fromJson(individualReceived, LogEntrySerial.class);
                    LoggedDataEntry LogFromDB = new LoggedDataEntry(LogFromDBserial.date, LogFromDBserial.time,
                            LogFromDBserial.hf, LogFromDBserial.hb, LogFromDBserial.tf, LogFromDBserial.tb,
                            LogFromDBserial.raf, LogFromDBserial.rab, LogFromDBserial.laf, LogFromDBserial.lab,
                            LogFromDBserial.rlf, LogFromDBserial.rlb, LogFromDBserial.llf, LogFromDBserial.llb,
                            LogFromDBserial.treatmentYorN, LogFromDBserial.treatmentUsed, LogFromDBserial.temperature,
                            LogFromDBserial.humidity, LogFromDBserial.pollutionLevel, LogFromDBserial.pollenLevel,
                            LogFromDBserial.location, LogFromDBserial.hfTreated, LogFromDBserial.hbTreated,
                            LogFromDBserial.tfTreated, LogFromDBserial.tbTreated, LogFromDBserial.rafTreated,
                            LogFromDBserial.rabTreated, LogFromDBserial.lafTreated, LogFromDBserial.labTreated,
                            LogFromDBserial.rlfTreated, LogFromDBserial.rlbTreated, LogFromDBserial.llfTreated,
                            LogFromDBserial.llbTreated, LogFromDBserial.notes);

                    System.out.println("Location from db: " + LogFromDB.location);

                    logList.add(LogFromDB);
                }

                Log.i("length", String.valueOf(logList.size()));
                System.out.println(logList.get(1).location);

                return data;
            } catch (Exception e) {
                Log.i("caught?", "unfortunately");
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}

    /*
    //parsing ArrayList<LogEntry> and separating values with commas
    public static String toCSV(String[] array){
        String result = "";
            if (array.length > 0) { StringBuilder sb = new StringBuilder();
                for (String s : array) {
                    sb.append(s).append(",");
                    }
                        result = sb.deleteCharAt(sb.length() - 1).toString();}
            return result;
    }

    //getrequest from database's data
    String url = "http://my-json-feed";

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    textView.setText("Response: " + response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error

                }
            });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    JSONArray jsonarray = new JSONArray(jsonStr);

        ArrayList List<LogEntry> = new ArrayList<>();

        //breaking down long string of data
        for (int i = 0; i < jsonarray.length(); i++) {
            Integer x,y;
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            System.out.println(jsonarray.get(i));
            String severity = jsonobject.getString("severity");
            List.add(x);
            String date = jsonobject.getString("date");
            float temp = jsonobject.getString("temperature");
            String pollen = jsonobject.getString("pollen");

            //assigning integer values for severity
            if(severity == "mild"){
                x = 0;
                if(severity == "moderate"){
                    x = 1;
                    if(severity == "severe"){
                        x = 2;
                    }
                }
            }

        }
    }

    //listing all elements of ArrayList for checking and testing
    private ArrayList<LogEntry> list = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
    }

    //random code
    private void drawlinechart(float [] yValues, String [] xValues){
        ArrayList<Entry> yData = new ArrayList<>();
        for (int i = 0; i < yValues.length; i++){
            for (int j = 0; j<xValues.length; j++) {
                yData.add(new Entry(i, yValues[i]));
            }
        }
    }

    //finally plotting data from database, still under work bcos there's error
    private ArrayList getData(){
		int x,y;
        ArrayList<LogEntry> entries = new ArrayList<>();
        entries.add(new Entry(x,y));
        entries.add(new Entry(1,1));
        entries.add(new Entry(1.5f, 1f));
        entries.add(new Entry(2f, 2f));
        entries.add(new Entry(3f, 2f));
        entries.add(new Entry(4f, 2f));
        entries.add(new Entry(5f, 2f));
        return entries;
    }
*/



