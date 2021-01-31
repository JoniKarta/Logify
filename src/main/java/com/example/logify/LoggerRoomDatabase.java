package com.example.logify;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Database(entities = {Logger.class}, version = 1)
public abstract class LoggerRoomDatabase extends RoomDatabase {

    public abstract LoggerDao loggerDao();

    private static volatile LoggerRoomDatabase loggerRoomDatabase;
    private static final Lock lock = new ReentrantLock();
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService dataWriteExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static LoggerRoomDatabase getInstance(final Context context){
        // double check locking

        if(loggerRoomDatabase == null){
            try{
                lock.lock();
                loggerRoomDatabase = Room.databaseBuilder(
                        context.getApplicationContext(),
                        LoggerRoomDatabase.class, "logger_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(mockData)
                        .build();

            }finally{
                lock.unlock();
            }
        }
        return loggerRoomDatabase;
    }


    public static RoomDatabase.Callback mockData = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            dataWriteExecutorService.execute(() -> {
                loggerRoomDatabase.loggerDao().insert(new Logger("ERROR", "Something went wrong", new Date()));
                loggerRoomDatabase.loggerDao().insert(new Logger("TRACE", "Something went wrong", new Date()));
                loggerRoomDatabase.loggerDao().insert(new Logger("DEBUG", "Something went wrong", new Date()));

            });
        }
    };

}
