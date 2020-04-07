package com.yuge.kotlindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number =0
        textView.text=number.toString()
        buttonAdd.setOnClickListener {
            number++
            textView.text=number.toString()
        }
        buttonMinus.setOnClickListener {
            number--
            textView.text=number.toString()
        }
    }
}
