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
import com.example.logifylib.adapters.LoggerRecyclerAdapter;
import com.example.logifylib.model.Logger;
import com.example.logifylib.spinner.LogLevelAdapter;
import com.example.logifylib.spinner.LogLevelManager;
import com.example.logifylib.utility.Direction;
import com.example.logifylib.viewmodel.LoggerViewModel;

import java.util.Date;


public class MainActivity extends AppCompatActivity {


//    private RecyclerView recyclerView;
//    private LoggerRecyclerAdapter loggerRecyclerAdapter;
//    private LoggerViewModel loggerViewModel;
//    private Spinner spinner;
//    private EditText search;
//    private Switch aSwitch;
    private Button button;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.another_layout);
//         button = findViewById(R.id.button);
//    //     LoggerActivity.startIntent(this);
//
////        setupViews();
//        button.setOnClickListener(e-> {
//            Intent intent =  new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//            finish();
//        });

        LoggerManager loggerManager = LoggerManager.getInstance(getApplication());
        loggerManager.getLoggerViewModel().loggerCount().observe(this, integer -> {
            Log.i("TAG", "onCreate: " + integer);
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < 100; i++){
                    try {
                        Thread.sleep(3000);
                        loggerManager.getLoggerViewModel().insert(new Logger(Logger.DEBUG, "Testing" + i, new Date()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        // LoggerActivity.startIntent(this);
        //startActivityForResult(LoggerActivity.getInance());
    }
}
//        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoggerViewModel.class);
//
//
//        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> loggerViewModel.setSortingValue(isChecked ? Direction.ASC.name() : Direction.DESC.name()));
//
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//            @Override
//            public void afterTextChanged(Editable s) { }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                loggerViewModel.setLogSearchValue(s.toString());
//            }});
//
//
//        loggerViewModel.getByFilters().observe(this, loggerList -> {
//            loggerRecyclerAdapter.submitList(loggerList);
//        });
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                loggerViewModel.delete(loggerRecyclerAdapter.getLoggerPositionAt(viewHolder.getAdapterPosition()));
//            }
//        }).attachToRecyclerView(recyclerView);
//
//
////        new Thread(() -> {
////            try {
////                for (int i = 0; i < 20; i++) {
////                    Log.i("TAG", "onCreate: in here");
////                    Thread.sleep(3000);
////                    loggerViewModel.insert(new Logger(Logger.WARNING, Logger.WARNING + ": " + i, new Date()));
////                }
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }).start();
////        new Thread(() -> {
////            try {
////                for (int i = 0; i < 20; i++) {
////                    Log.i("TAG", "onCreate: in here");
////                    Thread.sleep(10000);
////                    loggerViewModel.insert(new Logger(Logger.ERROR, Logger.ERROR + ": " + i, new Date()));
////                }
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }).start();
////        new Thread(() -> {
////            try {
////                for (int i = 0; i < 20; i++) {
////                    Log.i("TAG", "onCreate: in here");
////                    Thread.sleep(8000);
////                    loggerViewModel.insert(new Logger(Logger.DEBUG, Logger.DEBUG + ": " + i, new Date()));
////                }
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////        }).start();
//
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        loggerViewModel.setLogTypeValue(parent.getSelectedItem().toString());
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//
//
//    private void setupViews() {
//        spinner = findViewById(R.id.activity_main_spinner);
//        spinner.setAdapter(new LogLevelAdapter(this, LogLevelManager.getInstance().getLogLevelItemList()));
//        spinner.setOnItemSelectedListener(this);
//        recyclerView = findViewById(R.id.logger_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        loggerRecyclerAdapter = new LoggerRecyclerAdapter();
//        recyclerView.setAdapter(loggerRecyclerAdapter);
//        search = findViewById(R.id.activity_main_logger_search);
//        aSwitch = findViewById(R.id.activity_main_switch_direction);
//        aSwitch.setChecked(true);
//        View.OnFocusChangeListener ofcListener = new MyFocusChangeListener();
//        search.setOnFocusChangeListener(ofcListener);
//    }
//    /**
//     * Hide the keyboard with pressing on the screen
//     */
////    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
////        View view = this.getCurrentFocus();
////        if (imm != null && view != null) {
////            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
////        }
////        return super.onTouchEvent(event);
////    }
//    private class MyFocusChangeListener implements View.OnFocusChangeListener {
//
//        public void onFocusChange(View v, boolean hasFocus){
//
//            if(v.getId() == R.id.activity_main_logger_search && !hasFocus) {
//
//                InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//
//            }
//        }
//    }
//}