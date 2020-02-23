package com.yuge.words;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    private static WordDatabase INSTANCE;//instance  meaning 实例

    //synchronized 保证多线程访问数据库时 县城以排队方式访问
    static synchronized WordDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,WordDatabase.class,"data_word1")
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

}
