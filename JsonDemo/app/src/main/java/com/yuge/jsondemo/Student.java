package com.yuge.jsondemo;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("student_name")
    private String name;
    private transient int age;//transient 会使age 不被序列化
    private Score score;

    public Student(String name, int age, Score score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
