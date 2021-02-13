package com.example.logify;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logifylib.LoggerActivity;
import com.example.logifylib.model.Logger;
import com.example.logifylib.viewmodel.LoggerViewModel;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        LoggerActivity.loggerViewModel.insert(new Logger(Logger.OTHER,"Second Activity - onStop", new Date()));
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
