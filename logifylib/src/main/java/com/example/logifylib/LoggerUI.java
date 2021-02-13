package com.example.logifylib;

import com.example.logifylib.viewmodel.LoggerViewModel;

public class LoggerUI {

    private static LoggerViewModel loggerViewModel;
    private static LoggerUI loggerUI;



    private LoggerUI() {

    }

    public static LoggerUI getInstance() {
        if(loggerUI == null) {
            loggerUI = new LoggerUI();

        }
        return loggerUI;
    }


}
