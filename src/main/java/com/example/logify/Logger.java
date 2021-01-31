package com.example.logify;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "logger")
public class Logger {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String tag;

    private String msg;

    @TypeConverters({DateConverter.class})
    private Date date;


    public Logger(String tag, String msg, Date date) {
        this.tag = tag;
        this.msg = msg;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "Logger{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}
