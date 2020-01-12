package com.example.eczema_app.ui.graphs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

public class graphHumidity extends AppCompatActivity {
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_humidity);

        lineChart = (LineChart)findViewById(R.id.lineChart);
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
        final String[] dates = new String[]{"0%", "20%", "40%", "60%","80%","100%"};
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
        yAxisLeft.setAxisMaximum(2f);

        //plotting line chart
        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
        lineChart.animateX(2500);
        lineChart.invalidate();
        lineDataSet.setLineWidth(7);
    }

    //manually plotting sample data until API database comes online
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
