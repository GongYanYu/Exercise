package com.yuge.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
//        //Student student1=new Student("yuge",20);
//        Gson gson=new Gson();
//        //String jsonStudent1=gson.toJson(student1);
//        String jsonStudent1="{\"age\":20,\"name\":\"yuge\"}";
//        Student student1=gson.fromJson(jsonStudent1,Student.class);

        Gson gson=new Gson();
//        Student student2=new Student("yuge",20,new Score(150,150,150));
//        String jsonStudent2=gson.toJson(student2);

        Student student1=new Student("yuge1",20,new Score(150,150,150));
        Student student2=new Student("yuge",20,new Score(150,150,150));
        Student[] students={student1,student2};
        String jsonStudents=gson.toJson(students);

        List<Student> list= Arrays.asList(students),list1;
        String jsonStudentsList=gson.toJson(list);
        list1=gson.fromJson(jsonStudentsList,List.class);
        Type typeStudents=new TypeToken<List<Student>>(){}.getType();
    }
}
