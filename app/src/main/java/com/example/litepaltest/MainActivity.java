package com.example.litepaltest;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button add_ToDo;
    ImageButton pic;

    Button ToCalendar;
    Button ToTime;
    Button ToHabit;
    Button ToAnalysis;
    DBhelper dBhelper = new DBhelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);      //暂且默认首页面是待办事项

        //android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
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
        //跳转至增加待办事项页面
        addToDo();
    }

    //对底部按钮的响应
    private void bottomListener(){
        ToCalendar = (Button) findViewById(R.id.button_Calendar);
        ToTime = (Button) findViewById(R.id.button_Time);
        ToHabit = (Button) findViewById(R.id.button_Habit);
        ToAnalysis = (Button) findViewById(R.id.button_Analysis);

        //对"日程"按钮的响应，跳转至日程页面
        ToCalendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.calendarlist);
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

        //对"时间轴"按钮的响应，跳转至时间轴页面
        ToTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.timelist);
                Intent intent = new Intent(MainActivity.this, TimeActivity.class);
                startActivity(intent);
            }
        });

        //对"习惯"按钮的响应，跳转至习惯页面
        ToHabit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(MainActivity.this, HabitActivity.class);
                startActivity(intent);
            }
        });

        //对"分析"按钮的响应，跳转至分析页面
        ToAnalysis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //setContentView(R.layout.habitlist);
                Intent intent = new Intent(MainActivity.this, AnalysisActivity.class);
                startActivity(intent);
            }
        });
    }

    //对头像图片的响应
    private void picListener(){
        pic = (ImageButton) findViewById(R.id.profile);
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    //增加待办事项
    private void addToDo(){
        //add_ToDo = (Button) findViewById(R.id.title_todo).findViewById(R.id.title_add);
        add_ToDo = (Button)findViewById(R.id.title_add);

        add_ToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, New_ToDo_Activity.class);
                startActivity(intent);
            }
        });
    }

}
