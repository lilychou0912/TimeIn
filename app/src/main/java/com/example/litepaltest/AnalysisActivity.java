package com.example.litepaltest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AnalysisActivity extends AppCompatActivity {

    Button setting;
    ImageView pic;

    Button ToToDo;
    Button ToCalendar;
    Button ToHabit;
    Button ToTime;
    DBhelper dBhelper = new DBhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //点击底部按钮
        bottomListener();

        //点击左上图片
        picListener();

        //点击“设置”键
        //settingListener();
    }

    //对底部按钮的响应
    private void bottomListener(){
        ToToDo = (Button) findViewById(R.id.button_ToDo);
        ToCalendar = (Button) findViewById(R.id.button_Calendar);
        ToTime = (Button) findViewById(R.id.button_Time);
        ToHabit = (Button) findViewById(R.id.button_Habit);

        //对"待办"按钮的响应，跳转至待办页面
        ToToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.todolist);
                Intent intent = new Intent(AnalysisActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //对"日程"按钮的响应，跳转至日程页面
        ToCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.calendarlist);
                Intent intent = new Intent(AnalysisActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        //对"时间轴"按钮的响应，跳转至时间轴页面
        ToTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.timelist);
                Intent intent = new Intent(AnalysisActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        //对"习惯"按钮的响应，跳转至习惯页面
        ToHabit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(AnalysisActivity.this, HabitActivity.class);
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
                Intent intent = new Intent(AnalysisActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

}
