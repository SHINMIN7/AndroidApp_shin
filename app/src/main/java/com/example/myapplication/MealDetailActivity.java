package com.example.myapplication;


import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.example.myapplication.domain.Meal;
import com.example.myapplication.domain.MealRepository;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MealDetailActivity extends BaseActivity {

    MealRepository mealRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        mealRepository = new MealRepository(getApplication());

        TextView mealName = (TextView) findViewById(R.id.meal_names_view);
        TextView calorie = (TextView)  findViewById(R.id.calorie);
        TextView place = (TextView)  findViewById(R.id.place_view);
        TextView foodType =(TextView)  findViewById(R.id.food_type_view);
        TextView time =(TextView)  findViewById(R.id.time_view);
        TextView cost = (TextView) findViewById(R.id.cost_view);
        TextView review = (TextView) findViewById(R.id.review_view);
        ImageView imageView = (ImageView)findViewById(R.id.image_view);

        String mealFood = getIntent().getStringExtra("meal");
        Meal meal = mealRepository.findByFood(mealFood);

        try {
            mealName.setText("식사: " + meal.getFoods());
            setImage(imageView, meal.getPictureUri());
            calorie.setText("칼로리: " + Integer.toString(meal.getCalorie()));
            place.setText("학식 장소: " + meal.getPlace());
            foodType.setText("식사 종류: " + meal.getFoodType().name());
            time.setText("식사 시간: "+meal.getTime().toString());
            cost.setText("식사 비용: "+Integer.toString(meal.getPrice()));
            review.setText("음식 소감: "+meal.getReview());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"세부정보 로드에 실패했습니다",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void setImage(ImageView imageView, Uri pictureUri) throws FileNotFoundException, IOException {
        ContentResolver resolver = getContentResolver();
        InputStream instream = resolver.openInputStream(pictureUri);
        Bitmap imgBitmap = BitmapFactory.decodeStream(instream);
        imageView.setImageBitmap(imgBitmap);    // 선택한 이미지를 이미지뷰에 셋
        instream.close();   // 스트림 닫아주기
    }
}
