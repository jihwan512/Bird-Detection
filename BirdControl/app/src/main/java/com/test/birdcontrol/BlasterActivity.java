package com.test.birdcontrol;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BlasterActivity extends AppCompatActivity {
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blaster);
        myRef = FirebaseDatabase.getInstance().getReference("buzzerTest");
        Switch aSwitch = (Switch)findViewById(R.id.simple_switch);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView1);

        //스위치 클릭
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(BlasterActivity.this, "스위치-ON", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.speaker);
                    myRef.setValue(1);

                } else {
                    Toast.makeText(BlasterActivity.this, "스위치-OFF", Toast.LENGTH_SHORT).show();
                    imageView.setImageResource(R.drawable.speaker_off);
                    myRef.setValue(0);
                }
            }
        });


        }
}