package com.yuge.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //old way
        //val myViewModel=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java)

       /* use implementation 'androidx.fragment:fragment-ktx:1.3.0-alpha04'
        kotlinOptions{
            jvmTarget=1.8
        }*/
        val myViewModel by viewModels<MyViewModel>()
        myViewModel.number.observe(this, Observer {
            textView.text=it.toString()
        })
        button.setOnClickListener { myViewModel.addOne() }
    }
}
