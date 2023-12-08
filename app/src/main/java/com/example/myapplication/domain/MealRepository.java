package com.example.myapplication.domain;

import android.app.Application;

import java.util.List;

public class MealRepository {

    private MealDao mealDao;


    public MealRepository(Application application) {
        MealRoomDatabase db = MealRoomDatabase.getDatabase(application);
        mealDao = db.mealDao();
    }

    public void insert(Meal meal) {
        MealRoomDatabase.databaseWriteExecutor.execute(() -> {
            mealDao.insert(meal);
        });
    }

    public List<Meal> findAll() {
        return mealDao.findAll();
    }

    public Meal findByFood(String food) {
        return mealDao.findByFoodName(food);
    }

    public List<Meal> findLastOneMonth() {
        return mealDao.findLastOneMonth();
    }

    public void deleteAll() {
        mealDao.deleteAll();
    }
}
