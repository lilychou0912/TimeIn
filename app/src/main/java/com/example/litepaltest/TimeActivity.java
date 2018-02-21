package com.example.litepaltest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;

public class TimeActivity extends AppCompatActivity {

    Button add_Time;
    ImageView pic;

    Button ToToDo;
    Button ToCalendar;
    Button ToHabit;
    Button ToAnalysis;
    DBhelper dBhelper = new DBhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timelist);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //点击底部按钮
        bottomListener();

        //点击左上图片
        picListener();

        //点击“+”键
        //addListener();
    }

    //对底部按钮的响应
    private void bottomListener(){
        ToCalendar = (Button) findViewById(R.id.button_Calendar);
        ToHabit = (Button) findViewById(R.id.button_Habit);
        ToAnalysis = (Button) findViewById(R.id.button_Analysis);
        ToToDo = (Button) findViewById(R.id.button_ToDo);

        //对"待办"按钮的响应，跳转至日程页面
        ToToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.todolist);
                Intent intent = new Intent(TimeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //对"日程"按钮的响应，跳转至日程页面
        ToCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.calendarlist);
                Intent intent = new Intent(TimeActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        //对"习惯"按钮的响应，跳转至习惯页面
        ToHabit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(TimeActivity.this, HabitActivity.class);
                startActivity(intent);
            }
        });

        //对"分析"按钮的响应，跳转至分析页面
        ToAnalysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(TimeActivity.this, AnalysisActivity.class);
                startActivity(intent);
            }
        });
    }

    //对头像图片的响应
    private void picListener(){
        pic = (ImageView)findViewById(R.id.profile);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
