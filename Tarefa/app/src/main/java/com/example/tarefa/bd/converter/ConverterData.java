package com.example.tarefa.bd.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverterData {

    @TypeConverter
    public Long toLong(Calendar i){
        if(i != null){
            return i.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar toCalendar(Long i){
        Calendar instance = Calendar.getInstance();
        if(i != null){
            instance.setTimeInMillis(i);
        }
        return instance;
    }
}
