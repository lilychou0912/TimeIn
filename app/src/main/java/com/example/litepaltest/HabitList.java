package com.example.litepaltest;

import org.litepal.crud.DataSupport;

/**
 * Created by lenovo on 2018/1/22.
 */

public class HabitList extends DataSupport {
    private int id;
    private String name;
    private int days = 0;   //坚持天数

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays() {
        ++days;
    }       //每次加一
}
