package com.example.litepaltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Date;

public class New_ToDo_Activity extends AppCompatActivity {

    Button submit;
    Button back;

    EditText input_text;
    RadioGroup radioGroup_priority;
    RadioButton radioButton_priority;
    RadioGroup radioGroup_category;
    RadioButton radioButton_category;
    DatePicker input_deadline;
    TimePicker input_alarm;
    EditText input_note;
    DBhelper dBhelper = new DBhelper();

    String name;
    int priority;
    Date alarm;
    Date deadline;
    String note;
    String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_todo);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //设置timepicker时间格式为24小时制
        input_alarm = (TimePicker)findViewById(R.id.todo_alarm);
        input_alarm.setIs24HourView(true);

        //按下确认键
        submitListener();
        //按下返回键
        backListener();

    }

    //确认键监听
    private void submitListener(){
        submit = (Button) findViewById(R.id.todo_title_submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取用户输入内容
                getInput();
                //添加代办事项
                dBhelper.addToDoList(name, priority, alarm, deadline, note, category);
                finish();
            }
        });
    }

    //返回键监听
    private void backListener(){
        back = (Button)findViewById(R.id.todo_title_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //返回待办界面
                finish();
            }
        });
    }

    //获取用户输入内容
    private void getInput(){
        get_name();
        get_priority();
        get_category();
        get_deadline();
        get_alarm();
        get_note();
    }

    //获取待办事项名称
    private void get_name(){
        input_text = (EditText)findViewById(R.id.edit_text);
        name = input_text.getText().toString();
    }

    //获取待办事项优先级
    private void get_priority(){
        radioGroup_priority =(RadioGroup)findViewById(R.id.edit_radiopriority);
        radioButton_priority =(RadioButton)findViewById(radioGroup_priority.getCheckedRadioButtonId());
        String input_priority = radioButton_priority.getText().toString();
        switch (input_priority){
            case "紧急重要": priority = 1;
            case "紧急非重要": priority = 2;
            case "重要非紧急": priority = 3;
            case "非紧急非重要": priority = 4;
        }
    }

    //获取种类
    private void get_category(){
        radioGroup_category = (RadioGroup)findViewById(R.id.edit_radiotype);
        radioButton_category = (RadioButton)findViewById(radioGroup_category.getCheckedRadioButtonId());
        category = radioButton_category.getText().toString();
    }

    //获取截止时间
    private void get_deadline(){
        input_deadline = (DatePicker)findViewById(R.id.deadline);
        int year = input_deadline.getYear();
        int month = input_deadline.getMonth();
        int day = input_deadline.getDayOfMonth();
        deadline = new Date(year, month, day);
    }

    //获取闹铃时间
    private void get_alarm(){
        //input_alarm = (TimePicker)findViewById(R.id.todo_alarm);
        int hour = input_alarm.getCurrentHour();
        int minute = input_alarm.getCurrentMinute();
        alarm = new Date(0,0,0, hour, minute);
    }

    //获取备注
    private void get_note(){
        input_note = (EditText)findViewById(R.id.note);
        note = input_note.getText().toString();
    }
}

