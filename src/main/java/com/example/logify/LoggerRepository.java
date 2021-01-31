package com.example.logify;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LoggerRepository {


    private LoggerDao loggerDao;
    private LiveData<List<Logger>> liveData;


    public LoggerRepository(Application application){
        LoggerRoomDatabase loggerRoomDatabase = LoggerRoomDatabase.getInstance(application);
        loggerDao  = loggerRoomDatabase.loggerDao();
        liveData = loggerDao.getAllLogs();
    }


    public void insert(Logger logger){
       LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
           loggerDao.insert(logger);
       });
    }

    public LiveData<List<Logger>> getAllLogs() {
       return liveData;
    }
}
