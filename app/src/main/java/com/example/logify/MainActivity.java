package com.example.logify;

import androidx.annotation.RequiresApi;
import android.os.Build;
import android.os.Bundle;

import com.example.logifylib.LoggerUI;

public class MainActivity extends LoggerUI {

    private static final String TAG = "MainActivity";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getLoggerViewModel().deleteAll();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 1000; i++) {
//                    Thread.sleep(5000);
//                    getLoggerViewModel().insert(new Logger(Logger.INFO, "INFO", new Date()));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//
//        // This methods allows you to observe the changes and display the raw data to the console
//        getLoggerViewModel().getAllLogs().observe(this, loggers -> {
//            Log.i(TAG, "View logs: " + loggers);
//        });
//
//        getLoggerViewModel().loggerCount().observe(this, count -> {
//            Log.i(TAG, "Number of logs : " + count);
//        });

    }
    
}