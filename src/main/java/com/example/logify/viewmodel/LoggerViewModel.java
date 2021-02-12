package com.example.logify.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.logify.model.Logger;
import com.example.logify.repository.LoggerRepository;
import com.example.logify.utility.Direction;

import java.util.Date;
import java.util.List;

public class LoggerViewModel extends AndroidViewModel {

    private final LoggerRepository loggerRepository;
    private final MutableLiveData<String> sortingMutableLiveData;
    private final MutableLiveData<String> logTextSearchMutableLiveData;
    private final MutableLiveData<String> logFilterTypeMutableLiveData;
    private final CustomLiveData customLiveData;

    public LoggerViewModel(@NonNull Application application) {
        super(application);
        loggerRepository = new LoggerRepository(application);
        sortingMutableLiveData = new MutableLiveData<>();
        logTextSearchMutableLiveData = new MutableLiveData<>();
        logFilterTypeMutableLiveData = new MutableLiveData<>();
        customLiveData = new CustomLiveData(sortingMutableLiveData, logTextSearchMutableLiveData, logFilterTypeMutableLiveData);
    }

    public void insert(Logger logger) {
        loggerRepository.insert(logger);
    }

    public void insertMany(Logger... loggers) {
        loggerRepository.insertMany(loggers);
    }

    public void deleteAll() {
        loggerRepository.deleteAll();
    }

    public void delete(Logger logger) {
        loggerRepository.delete(logger);
    }

    public LiveData<List<Logger>> getAllLogs() {
        return loggerRepository.getAllLogs();
    }

    public LiveData<List<Logger>> getAllLogsByTagName(String tag) {
        return loggerRepository.getAllLogsByTagName(tag);
    }

    public LiveData<List<Logger>> getAllLogsByDateGreaterThan(Date start) {
        return loggerRepository.getAllLogsByDateGreaterThan(start);
    }

    public LiveData<List<Logger>> getAllLogsByMessageLike(String filterPattern) {
        return loggerRepository.getAllLogsByMessageLike(filterPattern);
    }

    public void setLogSearchValue(String value) {
        logTextSearchMutableLiveData.setValue(value);
    }

    public void setSortingValue(String direction) {
        sortingMutableLiveData.setValue(direction);
    }

    public void setLogTypeValue(String type) {
        logFilterTypeMutableLiveData.setValue(type);
    }


    public LiveData<List<Logger>> getByFilters() {
        return Transformations.switchMap(customLiveData, input -> {
            String sortBy = input.first == null ? Direction.ASC.name() : input.first;
            String textSearch = input.second == null ? "" : input.second;
            String filterType = input.third == null ? Logger.DEBUG: input.third;
            if (sortBy.equals(Direction.ASC.name())) {
                return loggerRepository.getAllByTagAndFilterValueLike(Direction.ASC.name(), textSearch, filterType);
            } else {
                return loggerRepository.getAllByTagAndFilterValueLike(Direction.DESC.name(), textSearch, filterType);
            }
        });
    }

    public static class CustomLiveData extends MediatorLiveData<Triple<String, String, String>> {
        public CustomLiveData(LiveData<String> sort, LiveData<String> textSearch, LiveData<String> filterType) {
            addSource(sort, s -> setValue(Triple.create(s, textSearch.getValue(), filterType.getValue())));
            addSource(textSearch, s -> setValue(Triple.create(sort.getValue(), s, filterType.getValue())));
            addSource(filterType, s -> setValue(Triple.create(sort.getValue(), textSearch.getValue(), s)));
        }
    }
}
