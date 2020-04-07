package com.yuge.kotlindemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    var number:MutableLiveData<Int> by lazy  { MutableLiveData<Int>().also { it.value=0 } }

//    init {
//        number=MutableLiveData()
//        number.value=0
//    }
}