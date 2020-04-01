package com.yuge.parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yuge.parcelable.databinding.ActivityMain2Binding;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActivityMain2Binding binding= DataBindingUtil.setContentView(this,R.layout.activity_main2);
        Intent intent=getIntent();
        Log.d("my", "onCreate: "+"ok");
        Bundle bundle=intent.getBundleExtra("data");
        Student student=bundle.getParcelable("student");

        binding.textViewName.setText(student.getName());
        binding.textViewAge.setText(String.valueOf(student.getAge()));
        binding.textViewMath.setText(String.valueOf(student.getScore().getMath()));
        binding.textViewEnglish.setText(String.valueOf(student.getScore().getEnglish()));

    }
}
