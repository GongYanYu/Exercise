package com.yuge.parcelable;
//嘿嘿
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuge.parcelable.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.editTextName.getText().toString();
                int age=Integer.parseInt(binding.editTextAge.getText().toString());
                int math=Integer.parseInt(binding.editTextMath.getText().toString());
                int english=Integer.parseInt(binding.editTextEnglish.getText().toString());
                Student student=new Student(name,age,new Score(math,english));

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                //intent.putExtra("student",student);    //建议用bundle
                Bundle bundle=new Bundle();
                bundle.putParcelable("student",student);
                intent.putExtra("data",bundle);

                startActivity(intent);


            }
        });
    }
}
