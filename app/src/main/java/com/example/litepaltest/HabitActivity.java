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

public class HabitActivity extends AppCompatActivity {
    Button add_Habit;

    ImageView pic;
    EditText newHabit;
    String name;
    LinearLayout habitlist;
    View new_habit;

    Button ToToDo;
    Button ToCalendar;
    Button ToTime;
    Button ToAnalysis;
    DBhelper dBhelper = new DBhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.habitlist);

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
        //增加习惯
        addHabit();
    }

    //对底部按钮的响应
    private void bottomListener(){
        ToToDo = (Button) findViewById(R.id.button_ToDo);
        ToCalendar = (Button) findViewById(R.id.button_Calendar);
        ToTime = (Button) findViewById(R.id.button_Time);
        ToAnalysis = (Button) findViewById(R.id.button_Analysis);

        //对"待办"按钮的响应，跳转至待办页面
        ToToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.todolist);
                Intent intent = new Intent(HabitActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //对"日程"按钮的响应，跳转至日程页面
        ToCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.calendarlist);
                Intent intent = new Intent(HabitActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        //对"时间轴"按钮的响应，跳转至时间轴页面
        ToTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.timelist);
                Intent intent = new Intent(HabitActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        //对"分析"按钮的响应，跳转至分析页面
        ToAnalysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(HabitActivity.this, AnalysisActivity.class);
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
                Intent intent = new Intent(HabitActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    //增加习惯
    private void addHabit(){
        //title_habit = findViewById(R.id.title_habit);
        add_Habit = (Button)findViewById(R.id.title_habit).findViewById(R.id.title_add);
        newHabit = (EditText)findViewById(R.id.newhabit);
        habitlist = (LinearLayout)findViewById(R.id.habitlist);
        new_habit = View.inflate(this,R.layout.habit_item,null);

        add_Habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newHabit.requestFocus();
            }
        });

        newHabit.setOnFocusChangeListener(new android.view.View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    //焦点消失时，获取习惯名称
                    name = newHabit.getText().toString();
                    newHabit.setText(null);
                    //添加习惯
                    dBhelper.addHabit(name);
                    //页面增加习惯
                    habitlist.addView(new_habit);
                }
            }
        });
    }
}
