package com.yuge.gallery

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson

const val DATA_CAN_LOAD_MORE=0
const val DATA_NOT_MORE=1
const val DATA_NET_WORK_ERROR=2
class GalleryViewModel(application: Application) : AndroidViewModel(application) {
    private val _dataStateLive =MutableLiveData<Int>()
    private var _photoListLive=MutableLiveData<List<PhotoItem>>()
    val dataStateLive:LiveData<Int> get() = _dataStateLive
    val photoListLive:LiveData<List<PhotoItem>>
    get()=_photoListLive

    var needToScrollTop=true
    private val perPage=100
    private var currentPage=1
    private var totalPage=1
    private var currentKey="yuge"
    private var isNewQuery=true
    private var isLoading=false
    private val keyWords= arrayOf("cat","leg","god","code","beautiful girl","grid","bird","beer","bulk","life","love","yuge")
    init {
        resetQuery()
    }

    fun fetchData(){
        if(isLoading) return
        isLoading=true
        if(currentPage>totalPage) {
            _dataStateLive.value= DATA_NOT_MORE
            return
        }
        val stringRequest = StringRequest(
            Request.Method.GET,
            getUrl(),
            Response.Listener {
                with(Gson().fromJson(it,Pixabay::class.java)){
                    totalPage=(totalHits-1)/perPage+1
                    if(isNewQuery){
                        _photoListLive.value=this.hits.toList()
                    }else{
                        _photoListLive.value= arrayListOf(_photoListLive.value!!,this.hits.toList()).flatten()
                    }
                }
                _dataStateLive.value= DATA_CAN_LOAD_MORE
                isLoading=false
                isNewQuery=false
                currentPage++
            },
            Response.ErrorListener {
                Log.d("My",it.toString())
                isLoading=false
                _dataStateLive.value= DATA_NET_WORK_ERROR
            }
        )
        VolleySingleton.getInstance(getApplication()).requestQueue.add(stringRequest)
    }
    fun resetQuery(){
        currentPage=1
        currentKey=keyWords.random()
        isNewQuery=true
        needToScrollTop=true
        fetchData()
    }

    private fun getUrl():String{
        return "https://pixabay.com/api/?key=15836521-9cc5f46f86652dcc72cebacc8&q=${currentKey}&per_page=${perPage}&page=${currentPage}"
    }

}