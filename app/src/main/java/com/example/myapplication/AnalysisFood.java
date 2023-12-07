package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;
import com.example.myapplication.domain.MealType;

import java.util.List;


public class AnalysisFood extends BaseActivity {

    MealRepository mealRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_food);
        mealRepository = new MealRepository(getApplication());

        TextView monthCalorie = (TextView) findViewById(R.id.month_calorie);
        TextView breakfast = (TextView) findViewById(R.id.breakfast_cost);
        TextView lunch = (TextView) findViewById(R.id.lunch_cost);
        TextView dinner = (TextView) findViewById(R.id.dinner_cost);
        TextView beverage = (TextView) findViewById(R.id.beverage_cost);


        List<Meal> lastOneMonth = mealRepository.findLastOneMonth();

        setTotalCalorie(monthCalorie, lastOneMonth);

        breakfast.setText("아침: "+getMonthCost(lastOneMonth, MealType.조식)+"원");
        lunch.setText("점심: "+getMonthCost(lastOneMonth, MealType.중식)+"원");
        dinner.setText("저녁: "+getMonthCost(lastOneMonth, MealType.석식)+"원");
        beverage.setText("음료: "+getMonthCost(lastOneMonth, MealType.음료)+"원");
    }

    private void setTotalCalorie(TextView monthCalorie, List<Meal> lastOneMonth) {
        int sumCalorie = lastOneMonth.stream()
                .mapToInt(Meal::getCalorie)
                .sum();

        monthCalorie.setText("최근 한 달 총 칼로리: "+ sumCalorie);
    }

    private int getMonthCost(List<Meal> meals, MealType mealType) {
        //한달 간의 식사 중 해당 식사탑입 인것을 걸러내서 가격 합하기
        return meals.stream()
                .filter(meal -> meal.getFoodType() == mealType)
                .mapToInt(Meal::getPrice)
                .sum();
    }

}


