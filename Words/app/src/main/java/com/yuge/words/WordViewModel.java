package com.yuge.words;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel  extends AndroidViewModel {
    private WordRepository myRepository;

    public WordViewModel(@NonNull Application application) {
        super(application);
        myRepository = new WordRepository(application);
    }

    LiveData<List<Word>> getAllWordsLive() {
        return myRepository.getAllWordsLive();
    }

    LiveData<List<Word>> findWordsWithPattern(String pattern){
        return myRepository.findWordsWithPattern(pattern);
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

    void deleteAllWords() {
        myRepository.deleteAllWords();
    }


}

