package com.yuge.roomexercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MyRepository myRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
    }

    LiveData<List<Word>> getAllWordsLive() {
        return myRepository.getAllWordsLive();
    }


    void insertWords(Word... words) {
        myRepository.insertWords(words);
    }

    void updateWords(Word... words) {
        myRepository.updateWords(words);
    }

    void deleteWords(Word... words) {
        myRepository.deleteWords(words);
    }

    void ClearWords() {
        myRepository.ClearWords();
    }


}
