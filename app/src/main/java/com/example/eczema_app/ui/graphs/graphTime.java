package com.example.eczema_app.ui.graphs;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.eczema_app.ui.HttpCommunicate;
import com.example.eczema_app.ui.LogEntrySerial;
import com.example.eczema_app.ui.home.LoggedDataEntry;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import com.example.eczema_app.R;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;


public class graphTime extends AppCompatActivity {
    private LineChart lineChart;

    String data = "";
    ArrayList<LoggedDataEntry> logList = new ArrayList<LoggedDataEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_time);

        lineChart = (LineChart) findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(getData(), "Severity levels");
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        //enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        //if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true);

        //x-axis code
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        //setting x-axis date
        final String[] dates = new String[]{"4/12/19", "5/12/19", "6/12/19", "7/12/19", "8/12/19", "9/12/19"};
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return dates[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        /*
        public ArrayList<String> queryXData(){
            ArrayList<String> xNewData = new ArrayList<String>();
            String query = "SELECT " + DAILY_DATE + " FROM " + TABLE_DAILY_FRAG;
            Cursor cursor = mSQLiteDatabase.rawQuery(query, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                xNewData.add(cursor.getString(cursor.getColumnIndex(DAILY_DATE)));
            }
            cursor.close();
            return xNewData;
        }

        public ArrayList<Float> queryYData(){
    ArrayList<Float> yNewData = new ArrayList<Float>();
    String query = "SELECT " + DAILY_TOTAL + " FROM " + TABLE_DAILY_FRAG;
    Cursor cursor = mSQLiteDatabase.rawQuery(query, null);
    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
        yNewData.add(cursor.getFloat(cursor.getColumnIndex(DAILY_TOTAL)));
    }
    cursor.close();
    return yNewData;
}

        private void addData(){

            ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();

            for (int i = 0; i < mExpenseDB.queryYData().size(); i++)
                yVals.add(new BarEntry(mExpenseDB.queryYData().get(i), i));

            ArrayList<String> xVals = new ArrayList<String>();
            for(int i = 0; i < mExpenseDB.queryXData().size(); i++)
                xVals.add(mExpenseDB.queryXData().get(i));

            BarDataSet dataSet = new BarDataSet(yVals, "expense values");
            dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

            BarData data = new BarData(xVals, dataSet);


            LimitLine line = new LimitLine(12f, "average daily expense");
            line.setTextSize(12f);
            line.setLineWidth(4f);
            YAxis leftAxis = barChart.getAxisLeft();
            leftAxis.addLimitLine(line);

            barChart.setData(data);
            barChart.setDescription("The expenses chart.");
            barChart.animateY(2000);

        }
        */

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
        yAxisLeft.setAxisMaximum(2f);

        //plotting line chart
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();
        lineDataSet.setLineWidth(7);
    }
//    //manually plotting sample data until API database comes online
//    private ArrayList getData(){
//        ArrayList<Entry> entries = new ArrayList<>();
//        entries.add(new Entry(0f, 0f));
//        entries.add(new Entry(1f, 1f));
//        entries.add(new Entry(2f, 2f));
//        entries.add(new Entry(3f, 2f));
//        entries.add(new Entry(4f, 1f));
//        entries.add(new Entry(5f, 1f));
//        return entries;
//    }

    private ArrayList getData() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < logList.size(); i++) {
            entries.add(new Entry(Integer.parseInt(logList.get(i).date), logList.get(i).severityScore));
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