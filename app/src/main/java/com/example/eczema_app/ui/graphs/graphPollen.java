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

public class graphPollen extends AppCompatActivity {

    private LineChart lineChart;

    String data = "";
    ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_pollen);

        lineChart = (LineChart)findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(getData(), "Severity levels");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        //enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        //if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);

        //setting x-axis pollen level
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] dates = new String[]{"none", "low°", "moderate°", "severe"};
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
        //
        yAxisLeft.setGranularity(1f);
        yAxisLeft.setValueFormatter(formatter1);

        //setting limits of y-axis to be 3 severity levels
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setAxisMaximum(2f);

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
            entries.add(new Entry(logList.get(i).severityScore, Integer.parseInt(logList.get(i).pollenLevel)));
        }
        return entries;
    }

//    //manually plotting sample data until API database comes online
//    private ArrayList getData(){
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(0f, 0f));
//        entries.add(new Entry(1f, 1f));
//        entries.add(new Entry(2f, 2f));
//        entries.add(new Entry(3f, 2f));
//        //entries.add(new Entry(4f, 1f));
//        //entries.add(new Entry(5f, 1f));
//        return entries;
//    }

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
