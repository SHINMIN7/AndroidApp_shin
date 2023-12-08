package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

public class InputFood extends BaseActivity {

    private MealRepository mealRepository;

    Uri foodPicture = Uri.parse("default");

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        ContentResolver resolver = getContentResolver();
                        try {
                            InputStream instream = resolver.openInputStream(uri);
                            Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
                            instream.close();   // 스트림 닫아주기
                            saveBitmapToJpeg(imgBitmap, "img"+ LocalDateTime.now().toString());    // 내부 저장소에 저장
                            foodPicture = uri;
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_food);
        mealRepository = new MealRepository(getApplication());

        generateSpinner1();
        generateSpinner2();


        //이미지 등록하기
        Button selectButton = (Button) findViewById(R.id.image_button);
        selectButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        activityResultLauncher.launch(intent);
                    }
                }

        );

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
                if (!isOnlyDigit(time) ) {
                    Toast.makeText(getApplicationContext(), "시간을 문자 없이 입력해주세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                int cost = Integer.valueOf(costText.getText().toString());
                String review = reviewText.getText().toString();

                //SQLite를 사용해서 room으로 db를 저장
                //ORM(room)을 사용해서 객체를 db에 저장
                mealRepository.insert(new Meal(mealName,foodPicture, place, foodType, time, cost, review));


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }

    private boolean isOnlyDigit(String time) {
        boolean isDigit = true;
        for (char c: time.toCharArray()) {
            if (!Character.isDigit(c)) {
                isDigit = false;
            }
        }
        return isDigit;
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

    public void saveBitmapToJpeg(Bitmap bitmap, String imgName) throws Exception {   // 선택한 이미지 내부 저장소에 저장
        File tempFile = new File(getApplicationContext().getFilesDir(), imgName);    // 파일 경로와 이름 넣기
        tempFile.createNewFile();   // 자동으로 빈 파일을 생성하기
        FileOutputStream out = new FileOutputStream(tempFile);  // 파일을 쓸 수 있는 스트림을 준비하기
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);   // compress 함수를 사용해 스트림에 비트맵을 저장하기
        out.close();    // 스트림 닫아주기
        Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
    }





}



