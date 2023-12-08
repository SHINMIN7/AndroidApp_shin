package com.example.myapplication.domain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);

    @Query("DELETE FROM meal")
    void deleteAll();

    @Query("SELECT * FROM meal")
    List<Meal> findAll();

    @Query("SELECT * FROM meal WHERE foods IS :food")
    Meal findByFoodName(String food);

    @Query("SELECT * FROM meal WHERE time >= datetime('now', '-1 month')")
    List<Meal> findLastOneMonth();




}