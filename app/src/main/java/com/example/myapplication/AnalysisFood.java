package com.example.myapplication;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;
import com.example.myapplication.domain.MealType;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
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

        PieChart pieChart = (PieChart)findViewById(R.id.piechart);


        List<Meal> lastOneMonth = mealRepository.findLastOneMonth();

        setTotalCalorie(monthCalorie, lastOneMonth);

        breakfast.setText("아침: "+getMonthCost(lastOneMonth, MealType.조식)+"원");
        lunch.setText("점심: "+getMonthCost(lastOneMonth, MealType.중식)+"원");
        dinner.setText("저녁: "+getMonthCost(lastOneMonth, MealType.석식)+"원");
        beverage.setText("음료: "+getMonthCost(lastOneMonth, MealType.음료)+"원");

        setChart(pieChart,getMonthCost(lastOneMonth, MealType.조식), getMonthCost(lastOneMonth, MealType.중식),
                getMonthCost(lastOneMonth, MealType.석식),getMonthCost(lastOneMonth, MealType.음료));
    }

    private void setChart(PieChart pieChart, int breakfast, int lunch, int dinner, int beverage) {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(18);
        pieChart.setHoleColor(Color.BLACK);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(breakfast,"아침"));
        yValues.add(new PieEntry(lunch,"점심"));
        yValues.add(new PieEntry(dinner,"저녁"));
        yValues.add(new PieEntry(beverage,"음료"));

        Description description = new Description();
        description.setText("식사 종류별 비용"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);

        PieDataSet dataSet = new PieDataSet(yValues,"식사 종류");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(15);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
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


