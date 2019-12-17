package com.example.eczema_app.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import com.example.eczema_app.R;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

public class graphTemp extends AppCompatActivity {

    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_temp);

        lineChart = (LineChart)findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(getData(), "Severity levels");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        /*
        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
         */
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        final String[] dates = new String[]{"-10°", "0°", "10°", "20°","30°","40°"};
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return dates[(int) value];
            }
        };
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);


        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = lineChart.getAxisLeft();

        //
        final String[] severity = new String[]{"Mild", "Moderate", "Severe"};
        ValueFormatter formatter1 = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return severity[(int) value];
            }
        };
        //
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();
        lineDataSet.setLineWidth(7);

    }
    private ArrayList getData(){
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 0f));
        entries.add(new Entry(1f, 1f));
        entries.add(new Entry(2f, 2f));
        entries.add(new Entry(3f, 2f));
        entries.add(new Entry(4f, 2f));
        entries.add(new Entry(5f, 2f));

        return entries;
    }
}
