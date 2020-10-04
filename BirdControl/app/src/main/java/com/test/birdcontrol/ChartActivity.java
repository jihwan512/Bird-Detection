package com.test.birdcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;


import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    private LineChart lineChart;
    ArrayList<Entry> entry_chart = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        lineChart = findViewById(R.id.chart1);
        LineData chartData = new LineData();


        entry_chart.add(new Entry(3,9));
        entry_chart.add(new Entry(4,15));
        entry_chart.add(new Entry(5,20));
        entry_chart.add(new Entry(8,102));
        entry_chart.add(new Entry(9,30));
        entry_chart.add(new Entry(10,80));
        entry_chart.add(new Entry(11,12));
        entry_chart.add(new Entry(12,2));

        LineDataSet lineDataSet = new LineDataSet(entry_chart, "bird detection");
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(1.8f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setFillColor(Color.rgb(104, 241, 175));
        lineDataSet.setFillAlpha(100);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);


        chartData.addDataSet(lineDataSet);
        chartData.setValueTextSize(9f);
        chartData.setDrawValues(false);

        lineChart.setData(chartData);
        lineChart.invalidate();
    }
}
