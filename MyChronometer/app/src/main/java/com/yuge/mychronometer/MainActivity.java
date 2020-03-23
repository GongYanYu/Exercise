package com.yuge.mychronometer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MyChronometer myChronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myChronometer=findViewById(R.id.meter);
        getLifecycle().addObserver(myChronometer);
    }
}
