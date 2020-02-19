package com.yuge.roomexercise;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)//主键 自动生成
    private int id;
    @ColumnInfo(name = "english_word") //可命名 默认与列变量名相同
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String meaning;
    @ColumnInfo(name = "foo_data")
    private boolean foo;//the true is that chinese meaning does not visible

    public boolean isFoo() {
        return foo;
    }

    public void setFoo(boolean foo) {
        this.foo = foo;
    }

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
