package com.haochen.competitionbrain.bean;

/**
 * Created by Haochen on 2016/12/29.
 */
public class Athlete extends Competitor {
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    private int age;
    private int sex;
    private int score;
    private String tel;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
