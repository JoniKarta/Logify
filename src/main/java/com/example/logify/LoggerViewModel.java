package com.example.logify;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LoggerViewModel extends AndroidViewModel {

    private LoggerRepository loggerRepository;
    private LiveData<List<Logger>> allLogs;
    public LoggerViewModel(@NonNull Application application) {
        super(application);
        loggerRepository = new LoggerRepository(application);
        allLogs = loggerRepository.getAllLogs();

    }


    public void insert(Logger logger) {
        loggerRepository.insert(logger);
    }

    public LiveData<List<Logger>> getAllLogs(){
        return allLogs;
    }
}
