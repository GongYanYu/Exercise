package com.yuge.first;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    public MyViewModel(@NonNull Application application, SavedStateHandle handle)  {
        super(application);
        this.handle=handle;
        if(!handle.contains("fPut")){
            handle.set("fPut","");
            handle.set("fAns","");
        }
    }
    public String getFormerPut() {
        return handle.get("fPut").toString();
    }
    public String getFormerAns() {
        return handle.get("fAns").toString();
    }

    public void setFormer(String former1,String former2) {
        handle.set("fPut",former1);
        handle.set("fAns",former2);
    }
}
