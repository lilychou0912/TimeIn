package com.example.litepaltest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CalendarActivity extends AppCompatActivity {

    Button add_Calendar;
    ImageView pic;

    Button ToToDo;
    Button ToTime;
    Button ToHabit;
    Button ToAnalysis;
    DBhelper dBhelper = new DBhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendarlist);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //点击底部按钮
        bottomListener();

        //点击左上图片
        picListener();

        //点击“+”键
        addListener();
    }

    //对"+"按钮的响应
    private void addListener(){
        //跳转至增加日程页面
        addCalendar();
    }

    //对底部按钮的响应
    private void bottomListener(){
        ToToDo = (Button) findViewById(R.id.button_ToDo);
        ToTime = (Button) findViewById(R.id.button_Time);
        ToHabit = (Button) findViewById(R.id.button_Habit);
        ToAnalysis = (Button) findViewById(R.id.button_Analysis);

        //对"待办"按钮的响应，跳转至日程页面
        ToToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.todolist);
                Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //对"时间轴"按钮的响应，跳转至时间轴页面
        ToTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.timelist);
                Intent intent = new Intent(CalendarActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        //对"习惯"按钮的响应，跳转至习惯页面
        ToAnalysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.analysis);
                Intent intent = new Intent(CalendarActivity.this, HabitActivity.class);
                startActivity(intent);
            }
        });

        //对"分析"按钮的响应，跳转至分析页面
        ToAnalysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(CalendarActivity.this, AnalysisActivity.class);
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
                Intent intent = new Intent(CalendarActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    //增加日程
    private void addCalendar(){
        //title_calendar = findViewById(R.id.title_calendar);
        add_Calendar = (Button)findViewById(R.id.title_calendar).findViewById(R.id.title_add);

        add_Calendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CalendarActivity.this, New_Calendar_Activity.class);
                startActivity(intent);
            }
        });
    }
}
