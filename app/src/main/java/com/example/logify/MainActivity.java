package com.example.logify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.logifylib.LoggerManager;
import com.example.logifylib.LoggerUI;
import com.example.logifylib.model.Logger;

import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private LoggerManager loggerManager;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);
        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        loggerManager = LoggerManager.getInstance(getApplication());
        button.setOnClickListener(e -> {
          //  startActivity(new Intent(MainActivity.this, LoggerUI.class));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i< 100; i++){
                        try {
                            Thread.sleep(10000);
                            LoggerManager.getInstance(getApplication()).getLoggerViewModel().insert(new Logger(Logger.ERROR, "ROMA", new Date()));
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
            }).start();

            LoggerManager.getInstance(getApplication()).getLoggerViewModel().getAllLogs().observe(this, loggers -> {
                Log.i("TAG", "onCreate: 0" + loggers);
            });

            LoggerManager.getInstance(getApplication()).getLoggerViewModel().getAllLogsByMessageLike("ROMA").observe(this,loggers -> {
                Log.i("TAG", "onCreate 1: " + loggers);
            });
        });

        button1.setOnClickListener(e-> {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));

        });

    }
}