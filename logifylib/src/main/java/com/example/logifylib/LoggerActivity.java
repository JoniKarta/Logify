package com.example.logifylib;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logifylib.adapters.LoggerRecyclerAdapter;
import com.example.logifylib.spinner.LogLevelAdapter;
import com.example.logifylib.spinner.LogLevelManager;
import com.example.logifylib.utility.Direction;
import com.example.logifylib.viewmodel.LoggerViewModel;
import com.google.android.material.switchmaterial.SwitchMaterial;


public class LoggerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private LoggerRecyclerAdapter loggerRecyclerAdapter;
    public static LoggerViewModel loggerViewModel;
    private EditText search;
    private Switch aSwitch;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();


        loggerViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(LoggerViewModel.class);
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> loggerViewModel.setSortingValue(isChecked ? Direction.ASC.name() : Direction.DESC.name()));
        loggerViewModel.getByFilters().observe(this, loggerList -> loggerRecyclerAdapter.submitList(loggerList));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loggerViewModel.setLogSearchValue(s.toString());
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                loggerViewModel.delete(loggerRecyclerAdapter.getLoggerPositionAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        loggerViewModel.setLogTypeValue(parent.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void setupViews() {
        setupSpinner();
        setupRecyclerView();
        setupSearch();
        setupSwitch();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.logger_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        loggerRecyclerAdapter = new LoggerRecyclerAdapter();
        recyclerView.setAdapter(loggerRecyclerAdapter);
    }

    private void setupSwitch() {
        aSwitch = findViewById(R.id.activity_main_switch_direction);
        aSwitch.setChecked(true);

    }

    private void setupSpinner() {
        Spinner spinner = findViewById(R.id.activity_main_spinner);
        spinner.setAdapter(new LogLevelAdapter(this, LogLevelManager.getInstance().getLogLevelItemList()));
        spinner.setOnItemSelectedListener(this);
    }

    private void setupSearch() {
        search = findViewById(R.id.activity_main_logger_search);
    }


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

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Log.i("TAG", "onBackPressed: ");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i("TAG", "onDestroy: ");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i("TAG", "onStop: ");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i("TAG", "onPause: ");
//    }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i("TAG", "onStart: ");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.i("TAG", "onResume: ");
//    }

//
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    Log.i("TAG", "onCreate: in here");
//                    Thread.sleep(3000);
//                    loggerViewModel.insert(new Logger(Logger.WARNING, Logger.WARNING + ": " + i, new Date()));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    Log.i("TAG", "onCreate: in here");
//                    Thread.sleep(10000);
//                    loggerViewModel.insert(new Logger(Logger.ERROR, Logger.ERROR + ": " + i, new Date()));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 20; i++) {
//                    Log.i("TAG", "onCreate: in here");
//                    Thread.sleep(8000);
//                    loggerViewModel.insert(new Logger(Logger.DEBUG, Logger.DEBUG + ": " + i, new Date()));
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();

}