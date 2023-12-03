package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;

import java.util.List;

public class ShowFood extends BaseActivity {

    private RecyclerView recyclerView;
    MealRepository mealRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);

        mealRepository = new MealRepository(getApplication());
        final MealListAdapter adapter = new MealListAdapter(new MealListAdapter.WordDiff());

        List<Meal> allMeals = mealRepository.findAll();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter.submitList(allMeals);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
