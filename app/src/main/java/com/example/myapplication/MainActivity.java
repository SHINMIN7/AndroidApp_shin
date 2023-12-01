package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //각 버튼을 눌렀을 때 화면 전환
        Button inputFoodButton = (Button) findViewById(R.id.inputFoodButton);
        inputFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InputFood.class);
                startActivity(intent);
            }
        });

        Button showfoodbutton = (Button) findViewById(R.id.showFoodButton);
        showfoodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowFood.class);
                startActivity(intent);
            }
        });

        Button analysisFood = (Button) findViewById(R.id.analysisFoodButton);
        analysisFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnalysisFood.class);
                startActivity(intent);
            }
        });



    }


}