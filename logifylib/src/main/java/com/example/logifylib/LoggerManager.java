package com.example.logifylib;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.logifylib.viewmodel.LoggerViewModel;

public class LoggerManager {

    public static LoggerManager loggerManager;
    public static LoggerViewModel loggerViewModel;

    private LoggerManager(Application application) {
        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory(application).create(LoggerViewModel.class);
    }

    public static LoggerManager getInstance(Application application) {
        if(loggerManager == null) {
            loggerManager = new LoggerManager(application);
        }
        return loggerManager;
    }

    public LoggerViewModel getLoggerViewModel() {
        return loggerViewModel;
    }
}
