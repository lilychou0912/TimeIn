package com.example.litepaltest;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Date;

public class New_Calendar_Activity extends AppCompatActivity {

    Button back;
    Button submit;

    EditText input_text;
    EditText input_location;
    EditText input_note;
    RadioGroup radioGroup_category;
    RadioButton radioButton_category;
    DatePicker input_startDate;
    TimePicker input_startTime;
    DatePicker input_endDate;
    TimePicker input_endTime;
    TimePicker input_alarm;
    RadioGroup cycleGroup;
    RadioButton cycleButton;
    DBhelper dBhelper = new DBhelper();

    String name;
    String location;
    Date startTime;
    Date endTime;
    Date alarm;
    String cycle;
    String note;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_calendar);

        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //设置timepicker时间格式为24小时制
        input_startTime = (TimePicker)findViewById(R.id.begin_time);
        input_endTime = (TimePicker)findViewById(R.id.terminal_time);
        input_alarm = (TimePicker)findViewById(R.id.calendar_alarm);
        input_startTime.setIs24HourView(true);
        input_endTime.setIs24HourView(true);
        input_alarm.setIs24HourView(true);

        //按下确认键
        submitListener();
        //按下返回键
        backListener();
    }

    //返回键监听
    private void backListener(){
        back = (Button)findViewById(R.id.calendar_title_back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //返回待办界面
                finish();
            }
        });
    }

    //确认键监听
    private void submitListener(){
        submit = (Button) findViewById(R.id.calendar_title_submit);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取用户输入内容
                getInput();
                //添加代办事项
                dBhelper.addCalendarList(name, location, startTime, endTime, alarm, cycle, note, category);
                //关闭此界面
                finish();
            }
        });
    }

    //获取用户输入内容
    private void getInput(){
        get_name();
        get_location();
        get_category();
        get_startTime();
        get_endTime();
        get_alarm();
        get_cycle();
        get_note();
    }

    //获取日程名称
    private void get_name(){
        input_text = (EditText)findViewById(R.id.input_schedules);
        name = input_text.getText().toString();
    }

    //获取地点
    private void get_location(){
        input_location = (EditText)findViewById(R.id.input_location);
        location = input_text.getText().toString();
    }

    //获取种类
    private void get_category(){
        radioGroup_category = (RadioGroup)findViewById(R.id.calendar_radiotype);
        radioButton_category = (RadioButton)findViewById(radioGroup_category.getCheckedRadioButtonId());
        category = radioButton_category.getText().toString();
    }

    //获取开始时间
    private void get_startTime(){
        input_startDate = (DatePicker)findViewById(R.id.begin_date);
        int year = input_startDate.getYear();
        int month = input_startDate.getMonth();
        int day = input_startDate.getDayOfMonth();
        //input_startTime = (TimePicker)findViewById(R.id.begin_time);
        int hour = input_startTime.getCurrentHour();
        int minute = input_startTime.getCurrentMinute();
        startTime = new Date(year, month, day, hour, minute);
    }

    //获取结束时间
    private void get_endTime(){
        input_endDate = (DatePicker)findViewById(R.id.terminal_date);
        int year = input_endDate.getYear();
        int month = input_endDate.getMonth();
        int day = input_endDate.getDayOfMonth();
        //input_endTime = (TimePicker)findViewById(R.id.terminal_time);
        int hour = input_endTime.getCurrentHour();
        int minute = input_endTime.getCurrentMinute();
        endTime = new Date(year, month, day, hour, minute);
    }

    //获取提醒时间
    private void get_alarm(){
        //input_alarm = (TimePicker)findViewById(R.id.calendar_alarm);
        int hour = input_alarm.getCurrentHour();
        int minute = input_alarm.getCurrentMinute();
        alarm = new Date(0, 0, 0, hour, minute);
    }

    //获取周期
    private void get_cycle(){
        cycleGroup = (RadioGroup)findViewById(R.id.repeat);
        cycleButton = (RadioButton)findViewById(cycleGroup.getCheckedRadioButtonId());
        String input_cycle = cycleButton.getText().toString();

        switch (input_cycle){
            case "每年": cycle = "year";
            case "每月": cycle = "month";
            case "每周": cycle = "week";
        }
    }

    //获取备注
    private void get_note(){
        input_note = (EditText)findViewById(R.id.input_other);
        note = input_note.getText().toString();
    }

}
