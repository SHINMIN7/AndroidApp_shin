package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;

class MealViewHolder extends RecyclerView.ViewHolder {
    private TextView tv_item_name;
    private TextView text_date;

    private MealViewHolder(View itemView) {
        super(itemView);
        tv_item_name = (TextView) itemView.findViewById(R.id.tv_item_name);
        text_date = (TextView) itemView.findViewById(R.id.text_date);
    }

    static MealViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        return new MealViewHolder(view);
    }

    public void bind(String foods, LocalDateTime time) {
        tv_item_name.setText(foods);
        text_date.setText(time.toString());
    }
}