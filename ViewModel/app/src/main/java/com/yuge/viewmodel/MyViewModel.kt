package com.yuge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.logging.Logger
import kotlin.math.log

class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _number = MutableLiveData<Int>().apply {
        if (!savedStateHandle.contains("number"))
            savedStateHandle.set("number", 0);

        this.value = savedStateHandle.get("number")
    }
    val number: LiveData<Int> = _number
    fun addOne() {
        _number.value = _number.value?.plus(1)
        savedStateHandle.set("number",_number.value)
        Log.d("my", "enter")//什么鬼

    }
}