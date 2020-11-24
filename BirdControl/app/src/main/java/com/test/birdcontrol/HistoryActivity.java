package com.test.birdcontrol;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryActivity extends Activity {
    private LineChart lineChart;
    Object result;
    String tmp;
    Integer int_tmp;
    ArrayList<Entry> entry_chart = new ArrayList<>();
    DatabaseReference myRef;
    ArrayList<String> arrayList = new ArrayList<>();
    String string_result;
    ArrayList<String> dateArr = new ArrayList<>();
    ArrayList<Integer> dayArr = new ArrayList<>();
    Integer index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        myRef = FirebaseDatabase.getInstance().getReference("logs");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                result = dataSnapshot.getValue(Object.class);
                string_result =result.toString();

                Pattern pattern = Pattern.compile("(\\d\\d-\\d\\d)");
                Matcher matcher = pattern.matcher(string_result);
                while (matcher.find()) {
                    if(matcher.group(1) ==  null)
                        break;
                    dateArr.add(matcher.group(1));
                }

                String[] stringArr = string_result.split("(\\d\\d-\\d\\d)");
                for(String tmp : stringArr){
                    int cnt = 0;
                    for(int i=0; i<tmp.length(); i++){
                        if(tmp.charAt(i) == ':') cnt++;
                    }
                    if(tmp.trim().length() == 1)continue;
                    dayArr.add(cnt);
                }

                for(int i =0; i<dateArr.size(); i++){
                    System.out.println(dateArr.get(i) + " : " + dayArr.get(i));
                }
                for(int i=0;i<dateArr.size();i++){
                    for(int j=0;j<dateArr.size()-i-1;j++){
                        if(dateArr.get(j).compareTo(dateArr.get(j+1))>0){
                            tmp = dateArr.get(j+1);
                            dateArr.set(j+1, dateArr.get(j));
                            dateArr.set(j,tmp);

                            int_tmp = dayArr.get(j+1);
                            dayArr.set(j+1, dayArr.get(j));
                            dayArr.set(j,int_tmp);
                        }
                    }
                }
                System.out.println("08-15".compareTo("08-19"));
                System.out.println("sorting");
                for(int i =0; i<dateArr.size(); i++){
                    System.out.println(dateArr.get(i) + " : " + dayArr.get(i));
                }

                lineChart = findViewById(R.id.chart1);
                LineData chartData = new LineData();
                ArrayList<Entry> values = new ArrayList<>();
                values.add(new Entry(index++, dayArr.get(0)));

                for (int i = 1; i < dateArr.size(); i++) {
                    System.out.println(dayArr.get(i));
                    if(dateArr.get(i).compareTo(dateArr.get(i-1))!=1){
                        Integer cnt = dateArr.get(i).compareTo(dateArr.get(i-1));
                        System.out.println("0 insert : " + cnt);
                        while(cnt != 1){
                            values.add(new Entry(index++, 0));
                            cnt--;
                        }
                    }
                    values.add(new Entry(index++, dayArr.get(i)));
                }

                for (int i = 1; i < dateArr.size(); i++) {
                    System.out.println(dayArr.get(i));

                }

                LineDataSet set1;
                set1 = new LineDataSet(values, "DataSet 1");

                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(set1); // add the data sets

                // create a data object with the data sets
                LineData data = new LineData(dataSets);

                // black lines and points
                set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                set1.setCubicIntensity(0.2f);
                set1.setDrawFilled(true);
                set1.setDrawCircles(false);
                set1.setLineWidth(1.8f);
                set1.setCircleRadius(4f);
                set1.setCircleColor(Color.WHITE);
                set1.setHighLightColor(Color.rgb(244, 117, 117));
                set1.setColor(Color.WHITE);
                set1.setFillColor(Color.rgb(104, 241, 175));
                set1.setFillAlpha(100);
                set1.setDrawHorizontalHighlightIndicator(false);


                chartData.addDataSet(set1);
                chartData.setValueTextSize(9f);
                chartData.setDrawValues(false);

                lineChart.setData(chartData);
                lineChart.invalidate();

                // set data
                lineChart.setData(data);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(postListener);





    }
}
