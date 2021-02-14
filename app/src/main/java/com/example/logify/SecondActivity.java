package com.example.logify;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logifylib.LoggerManager;
import com.example.logifylib.LoggerUI;
import com.example.logifylib.model.Logger;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    private Button add;
    private Button home;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        home = findViewById(R.id.button2);
        add = findViewById(R.id.button3);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, LoggerUI.class));

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //startActivity(new Intent(SecondActivity.this, MainActivity.class));
                LoggerManager.getInstance(getApplication()).getLoggerViewModel().insert(new Logger(Logger.ERROR, "ERRRRR", new Date()));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("TAG", "Second Activity - onBackPressed: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "Second Activity - onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "Second Activity - onStop: ");
//        LoggerActivity.loggerViewModel.insert(new Logger(Logger.OTHER,"Second Activity - onStop", new Date()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "Second Activity - onPause: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "Second Activity - onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "Second Activity - onResume: ");
    }
}
