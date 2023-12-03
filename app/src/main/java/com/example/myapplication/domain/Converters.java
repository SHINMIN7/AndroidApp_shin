package com.example.myapplication.domain;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {

    private static final String localDateTimePattern = "yyyy-MM-dd HH:mm:ss";

    @TypeConverter
    public String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(localDateTimePattern);
        return localDateTime.format(formatter);
    }

    @TypeConverter
    public LocalDateTime stringToLocalDateTime(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(localDateTimePattern);
        return LocalDateTime.parse(string, formatter);
    }

    @TypeConverter
    public String mealTypeToString(MealType mealType) {
        return mealType.name();
    }

    @TypeConverter
    public MealType stringToMealType(String string) {
        return MealType.valueOf(string);
    }
}