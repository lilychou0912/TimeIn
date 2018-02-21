package com.example.litepaltest;

import android.provider.ContactsContract;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/1/23.
 */

public class DBhelper {
    static int toDoId = 0;
    static int calendarId = 0;
    static int inTimeId = 0;
    static int habitId = 0;

    public DBhelper(){
        LitePal.getDatabase();
    }

    //ToDoList

    //增加待办事项
    public void addToDoList(String name, int priority, Date alarm, Date deadline, String note, String category){
        //
        //不可重名，判断是否有相同name的待办事项（未写）
        //
        ToDoList toDo = new ToDoList();
        toDo.setName(name);
        toDo.setId(++toDoId);
        toDo.setAlarm(alarm);
        toDo.setCategory(category);
        toDo.setDeadline(deadline);
        toDo.setPriority(priority);
        toDo.setNote(note);
        toDo.save();
        //costTime由计算得，不由用户设定
    }

    //更新（修改）待办事项
    //点入修改界面时，先使oldname=此时的名字，再跳转页面
    public void updateToDoList(String oldname, String name, int priority, Date alarm, Date deadline, String note, String category){
        ToDoList toDo = new ToDoList();
        toDo.setName(name);
        toDo.setAlarm(alarm);
        toDo.setCategory(category);
        toDo.setDeadline(deadline);
        toDo.setPriority(priority);
        toDo.setNote(note);
        toDo.updateAll("name = ?", oldname);
    }

    //删除待办事项
    public void deleteToDoList(String oldname){
        DataSupport.deleteAll(ToDoList.class, "name = ?", "oldname");
    }

    //待办事项开始计时,用Chronometer类

    //Calendar

    //增加日程
    //locaition可由定位获得
    public void addCalendarList(String name, String location, Date startTime, Date endTime, Date alarm, String cycle, String note, String category){
        //
        //可以重名，时间不能和别的日程重叠，判断时间是否重叠
        //
        CalendarList calendar = new CalendarList();
        calendar.setId(++calendarId);
        calendar.setName(name);
        calendar.setLocation(location);
        calendar.setStartTime(startTime);
        calendar.setEndTime(endTime);
        calendar.setAlarm(alarm);
        calendar.setCycle(cycle);
        calendar.setNote(note);
        calendar.setCategory(category);
        calendar.save();
    }

    //更新（修改）日程
    public void updateCalendarList(String oldname, String name, String location, Date startTime, Date endTime, Date alarm, String cycle, String note, String category){
        CalendarList calendar = new CalendarList();
        calendar.setName(name);
        calendar.setLocation(location);
        calendar.setStartTime(startTime);
        calendar.setEndTime(endTime);
        calendar.setAlarm(alarm);
        calendar.setCycle(cycle);
        calendar.setNote(note);
        calendar.setCategory(category);
        //
        //判断时间是否有冲突
        //
        calendar.updateAll("name = ?", oldname);
    }

    //删除日程
    public void deleteCalendarList(String oldname){
        DataSupport.deleteAll(CalendarList.class, "name = ?", "oldname");
    }

    //将日程同步至时间轴（在通过定位确认之后）

    //Habit

    //增加习惯
    public void addHabit(String name){
        //
        //检测不重名
        //
        HabitList habit = new HabitList();
        habit.setId(++habitId);
        habit.setName(name);
        habit.save();
    }

    //更新（修改）习惯
    public void updateHabit(String oldname, String name){
        HabitList habit = new HabitList();
        habit.setName(name);
        //
        //检测重名
        //
        habit.updateAll("name = ?", oldname);
    }

    //删除习惯
    public void deleteHabit(String oldname){
        DataSupport.deleteAll(HabitList.class, "name = ?", "oldname");
    }

    //Time

    //增加时间记录
    public void addTime(String name, String note, String category, Date startTime, Date endTime){
        InTimeList time = new InTimeList();
        time.setId(++inTimeId);
        time.setName(name);
        time.setNote(note);
        time.setCategory(category);
        time.setStartTime(startTime);
        time.setEndTime(endTime);
        time.save();
    }

    //更新（修改）时间记录
    public void updateTime(String oldname, String name, String note, String category, Date startTime, Date endTime) {
        InTimeList time = new InTimeList();
        time.setName(name);
        time.setNote(note);
        time.setCategory(category);
        time.setStartTime(startTime);
        time.setEndTime(endTime);
        time.updateAll("name = ?", oldname);
    }

    //删除时间记录
    public void deleteTime(String oldname){
        DataSupport.deleteAll(InTimeList.class, "name = ?", "oldname");
    }

    //不同category时间统计
    public long getCategoryTimeSum(String category){
        List<InTimeList> timeList = DataSupport.where("category = ?", category)
                                               .select("startTime", "endTime")
                                               .find(InTimeList.class);
        long sum = 0;
        for (InTimeList time: timeList){
            sum += time.getEndTime().getTime() - time.getStartTime().getTime();
        }

        return sum;     //返回总毫秒
    }

    //统计总时间
    public long getAllTimeSum(){
        List<InTimeList> timeList = DataSupport.select("startTime", "endTime").find(InTimeList.class);
        long sum = 0;
        for (InTimeList time: timeList){
            sum += time.getEndTime().getTime() - time.getStartTime().getTime();
        }

        return sum;
    }

    //给定时间范围内，统计某category的总时间
    //date比大小用String
    public long getCategoryPeriodTimeSum(String category, String start, String end){
        List<InTimeList> timeList = DataSupport.where("category = ?", category)
                                               .where("startTime between ? and ?", start, end)
                                               .select("startTime", "endTime")
                                               .find(InTimeList.class);
        long sum = 0;
        for (InTimeList time: timeList){
            sum += time.getEndTime().getTime() - time.getStartTime().getTime();
        }

        return sum;     //返回总毫秒
    }
}