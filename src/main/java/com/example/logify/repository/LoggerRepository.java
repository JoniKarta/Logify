package com.example.logify.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.logify.model.Logger;

import java.util.Date;
import java.util.List;

public class LoggerRepository {


    private final LoggerDao loggerDao;

    public LoggerRepository(Application application) {
        LoggerRoomDatabase loggerRoomDatabase = LoggerRoomDatabase.getInstance(application);
        loggerDao = loggerRoomDatabase.loggerDao();
    }


    public void insert(Logger logger) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.insert(logger);
        });
    }

    public void insertMany(Logger... loggers) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.insertMany(loggers);
        });
    }

        public void delete(Logger logger) {
        LoggerRoomDatabase.dataWriteExecutorService.execute(() -> {
            loggerDao.delete(logger);
        });
    }
    public void deleteAll() {
        LoggerRoomDatabase.dataWriteExecutorService.execute(loggerDao::deleteAll);
    }

    public LiveData<List<Logger>> getAllLogs() {
        return loggerDao.getAllLogs();
    }

    public LiveData<List<Logger>> getAllLogsByTagName(String tag) {
        return loggerDao.getAllLogsByTagName(tag);
    }

    public LiveData<List<Logger>> getAllLogsByDateGreaterThan(Date start) {
        return loggerDao.getAllLogsByDateGreaterThan(start);
    }

    public LiveData<List<Logger>> getAllLogsByMessageLike(String filterPattern) {
        return loggerDao.getAllLogsByMessageLike(filterPattern);
    }



    public LiveData<List<Logger>> getAllByTagAndFilterValueLike(String direction, String filterPattern,String tag) {
        return loggerDao.getAllByTagAndFilterValueLike(direction, filterPattern,tag);
    }
}