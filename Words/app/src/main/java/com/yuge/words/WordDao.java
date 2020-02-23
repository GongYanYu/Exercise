package com.yuge.words;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insertWords(Word...words);

    @Update
    void updateWords(Word...words);

    @Delete
    void deleteWords(Word...words);

    @Query("delete from word")
    void deleteAllWords();

    @Query("select * from word order by id desc")
    LiveData<List<Word>> getAllWordsLive();

    @Query("select * from word where english_word like :pattern order by id desc")//:pattern 中间不可以加入空格
    LiveData<List<Word>> findWordsWithPattern(String pattern);
}
