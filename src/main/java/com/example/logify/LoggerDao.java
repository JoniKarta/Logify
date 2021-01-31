package com.example.logify;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LoggerDao {


    @Insert()
    void insert(Logger logger);

    @Query("SELECT * FROM logger ORDER BY date DESC")
    LiveData<List<Logger>> getAllLogs();
}
