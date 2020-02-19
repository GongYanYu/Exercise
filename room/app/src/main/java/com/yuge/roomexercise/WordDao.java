package com.yuge.roomexercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // database access object
public interface WordDao {
    @Insert
    void insertWords(Word...Words);// return int ,parameter can be only
    @Update
    void updateWords(Word...Words);
    @Delete
    void deleteWords(Word...Words);
    @Query("delete from Word")
    void deleteAllWords();
    @Query("select *from word order by id desc")//inserted time as order.
    //List<Word> getAllWords();
    LiveData<List<Word>> getAllWordsLive();
}
