package com.yuge.words;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "english_word")
    private String word;
    @ColumnInfo(name = "chinese_meaning")
    private String chinese_meaning;
    @ColumnInfo(name = "chineseInvisible")
    private boolean chinese_invisible;

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

    public String getChinese_meaning() {
        return chinese_meaning;
    }

    public void setChinese_meaning(String chinese_meaning) {
        this.chinese_meaning = chinese_meaning;
    }

    public boolean isChinese_invisible() {
        return chinese_invisible;
    }

    public void setChinese_invisible(boolean chinese_invisible) {
        this.chinese_invisible = chinese_invisible;
    }
}
