package com.example.logify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.logifylib.LoggerActivity;
import com.example.logifylib.LoggerManager;
import com.example.logifylib.LoggerUI;
import com.example.logifylib.adapters.LoggerRecyclerAdapter;
import com.example.logifylib.model.Logger;
import com.example.logifylib.spinner.LogLevelAdapter;
import com.example.logifylib.spinner.LogLevelManager;
import com.example.logifylib.utility.Direction;
import com.example.logifylib.viewmodel.LoggerViewModel;

import java.util.Date;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_layout);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(e -> {
            startActivity(
                    LoggerUI
                    .getInstance(this).
                            createLoggerIntent());
            finish();
        });

    }
}