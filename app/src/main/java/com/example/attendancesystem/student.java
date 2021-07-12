package com.example.attendancesystem;

public class student {

    int NUM;
    String Roll;

    public student(int NUM, String Roll) {

        this.NUM = NUM;
        this.Roll = Roll;
    }

    public student(){}

    public int getNUM() {

        return NUM;
    }

    public void setNUM(int NUM) {

        this.NUM = NUM;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String roll) {
        this.Roll = roll;
    }
}
