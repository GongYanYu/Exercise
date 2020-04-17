package com.yuge.gallerykotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

class GalleryViewModel(application: Application) :AndroidViewModel(application) {
    private var _photoListLive =MutableLiveData<List<PhotoItem>>()
    val photoListLive:LiveData<List<PhotoItem>>
    get()=_photoListLive
    fun fetchData(){
        val stringRequest=StringRequest(
            Method.GET,
            getURL(),
            Response.Listener {
                _photoListLive.value= Gson().fromJson(it,Pixabay::class.java).hits
            },
            Response.ErrorListener {  }
        )

        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)

    }

    private fun getURL(): String {
        return "https://pixabay.com/api/?key=15836521-9cc5f46f86652dcc72cebacc8&q=${keyWord.random()}&per_page=100"
    }
    private val keyWord= arrayOf("kangaroo","rat","deer","sheep","zebra","female+leg","sexy","beautiful+sexy","lobster")
}