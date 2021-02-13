package com.example.logifylib;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.logifylib.model.Logger;
import com.example.logifylib.viewmodel.LoggerViewModel;

import java.util.List;

public final class LoggerUI {

    private static LoggerViewModel loggerViewModel;
    private static LoggerUI loggerUI;
    private final Intent intent;

    private LoggerUI(Context context) {
        intent = new Intent();
        intent.setComponent(new ComponentName(context.getPackageName(),
                LoggerActivity.class.getName()));
    }

    public synchronized static LoggerUI getInstance(Context context){
        if(loggerUI == null){
            loggerUI = new LoggerUI(context);
            loggerViewModel = new ViewModelProvider.AndroidViewModelFactory((Application)context.getApplicationContext()).create(LoggerViewModel.class);
        }
        return loggerUI;
    }

    public Intent createLoggerIntent(){
        return intent;
    }

    public void insert(Logger logger) {
        loggerViewModel.insert(logger);
    }

    public void insertMany(Logger... loggers){
        loggerViewModel.insertMany(loggers);
    }

    public LiveData<List<Logger>> getByFilters() {
        return loggerViewModel.getByFilters();
    }
}
