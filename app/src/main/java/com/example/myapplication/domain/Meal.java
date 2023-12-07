package com.example.myapplication.domain;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Entity(tableName = "meal")
public class Meal {

    private static Random random = new Random();

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String foods;

    private String picture;

    private String place;

    private MealType foodType;

    private LocalDateTime time;
    private int calorie;
    private int price;
    private String review;

    public Meal(String foods,
                String picture,
                String place,
                MealType foodType,
                LocalDateTime time,
                int price,
                String review) {
        this.id = id;
        this.foods = foods;
        this.picture = picture;
        this.place = place;
        this.foodType = foodType;
        this.time = time;
        this.price = price;
        this.review = review;

        this.calorie =  100*( random.nextInt(9 + 1) + 1);
    }

    public Meal(String foods,
                Uri pictureUri,
                String place,
                String foodType,
                String time,
                int price,
                String review) {
        this.foods = foods;
        this.picture = pictureUri.toString();
        this.place = place;
        this.foodType = MealType.valueOf(foodType);
        LocalTime localTime = LocalTime.of(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2)));
        this.time = LocalDate.now().atTime(localTime);
        this.price = price;
        this.review = review;

        this.calorie =  100*(random.nextInt(9 + 1) + 1);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setFoodType(String foodType) {
        this.foodType = MealType.valueOf(foodType);
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getReview() {
        return review;
    }

    public int getPrice() {
        return price;
    }

    public String getFoods() {
        return foods;
    }

    public String getPlace() {
        return place;
    }

    public MealType getFoodType() {
        return foodType;
    }

    public String getFoodTypeName() {
        return foodType.name();
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setFoodType(MealType foodType) {
        this.foodType = foodType;
    }

    public String getPicture() {
        return picture;
    }

    public Uri getPictureUri() {
        return Uri.parse(picture);
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
