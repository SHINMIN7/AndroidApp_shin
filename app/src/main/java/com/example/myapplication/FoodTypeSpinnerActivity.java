package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class FoodTypeSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item is selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos).
        Spinner spinner = (Spinner) findViewById(R.id.food_type_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }
}
