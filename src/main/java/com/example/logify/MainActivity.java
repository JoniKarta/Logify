package com.example.logify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoggerViewModel noteViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication()).create(LoggerViewModel.class);

        noteViewModel.getAllLogs().observe(this, loggers ->{
            loggers.forEach(log-> {
                    Log.i("TAG", log.toString());
                });
        });

        new Thread(()->{
            try {
                for(int i = 0 ; i < 4; i++) {
                    Thread.sleep(5000);
                    noteViewModel.insert(new Logger("TAGGGGER", "SOMETHIGN", new Date()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}