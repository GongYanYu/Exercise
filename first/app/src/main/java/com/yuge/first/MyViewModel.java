package com.yuge.first;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    private static final String former = "former";
    public MyViewModel(@NonNull Application application, SavedStateHandle handle)  {
        super(application);
        this.handle=handle;
        if(!handle.contains(former)){
            handle.set(former,"");
        }
    }
    public String getFormer() {
        return handle.get(former).toString();
    }

    public void setFormer(String f) {
        handle.set(former,f);
    }
}
