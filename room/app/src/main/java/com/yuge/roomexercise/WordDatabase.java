package com.yuge.roomexercise;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//singleton   保证实例只有一个 以免多个数据库实例很好资源
@Database(entities = {Word.class},version = 4,exportSchema = false)
public abstract class WordDatabase  extends RoomDatabase {
    private static WordDatabase INSTANCE;//instance  meaning 实例

    //synchronized 保证多线程访问数据库时 县城以排队方式访问
    static synchronized WordDatabase getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,WordDatabase.class,"data_word")
                    //.fallbackToDestructiveMigration()//delete all data
                    .addMigrations(MIGRATION_3_4)
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table word add column foo_data integer not null default 1");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {//想删除多余的列
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table word_temp (id integer primary key not null,english_word text," +
                    "chinese_meaning text)");//  只能先创建一个新表
            database.execSQL("insert into word_temp (id,english_word,chinese_meaning) " +
                    "select id,english_word,chinese_meaning from word");// 复制数据到新建的那个表
            database.execSQL("drop table word");
            database.execSQL("alter table word_temp rename to word");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("alter table word add column foo_data integer not null default 0");
        }
    };

}
