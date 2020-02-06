package com.yuge.enheng;

import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends androidx.lifecycle.ViewModel {
    private MutableLiveData<Integer> likedNumber;


    public MutableLiveData<Integer> getLikedNumber() {
        if(likedNumber==null){
            likedNumber=new MutableLiveData<>();
            likedNumber.setValue(0);
        }
        return likedNumber;
    }

    public void addLikedNumber(int n){
        likedNumber.setValue(likedNumber.getValue()+n);
    }
}
