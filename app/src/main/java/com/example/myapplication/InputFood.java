package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;

public class InputFood extends BaseActivity {

    private MealRepository mealRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_food);
        mealRepository = new MealRepository(getApplication());

        generateSpinner1();
        generateSpinner2();

        //이미지 등록하기
//        Button selectButton = (Button) findViewById(R.id.image_button);
//        selectButton.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View view) {
//                                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                                                intent.setAction(Intent.ACTION_PICK);
//                                                activityResultLauncher.launch(intent);
//                                            }
//                                        }
//
//        );

        EditText mealNameText = findViewById(R.id.meal_names);
        EditText timeText = findViewById(R.id.time);
        Spinner placeSpinner = (Spinner) findViewById(R.id.place_spinner);
        Spinner foodTypeSpinner = (Spinner) findViewById(R.id.food_type_spinner);

        EditText costText = findViewById(R.id.cost);
        EditText reviewText = findViewById(R.id.review);

        View sendButton = findViewById(R.id.register_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mealName = mealNameText.getText().toString();
                String place = placeSpinner.getSelectedItem().toString();
                String foodType = foodTypeSpinner.getSelectedItem().toString();

                String time = timeText.getText().toString();
                int cost = Integer.valueOf(costText.getText().toString());
                String review = reviewText.getText().toString();

                //SQLite를 사용해서 room으로 db를 저장
                //ORM(room)을 사용해서 객체를 db에 저장
                mealRepository.insert(new Meal(mealName, place, foodType, time, cost, review));

            }
        });

    }

    private void generateSpinner2() {
        Spinner spinner2 = (Spinner) findViewById(R.id.food_type_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.food_type_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner2.setAdapter(adapter2);
    }

    private void generateSpinner1() {
        Spinner spinner1 = (Spinner) findViewById(R.id.place_spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.place_array,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner1.setAdapter(adapter1);
    }


}

