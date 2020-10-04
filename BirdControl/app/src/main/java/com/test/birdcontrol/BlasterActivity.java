package com.test.birdcontrol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button b10 = (Button)findViewById(R.id.button10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.setValue(1);
            }
        });
    }
}