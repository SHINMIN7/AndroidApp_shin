package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.myapplication.domain.Meal;

public class MealListAdapter extends ListAdapter<Meal, MealViewHolder> {


    public MealListAdapter(@NonNull DiffUtil.ItemCallback<Meal> diffCallback) {
        super(diffCallback);
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MealViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {
        Meal current = getItem(position);
        holder.bind(current.getFoods(), current.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                Context context = v.getContext();
                Intent intent = new Intent(context, MealDetailActivity.class);
                intent.putExtra("meal", current.getFoods());
                context.startActivity(intent);

            }
        });
    }

    static class WordDiff extends DiffUtil.ItemCallback<Meal> {

        @Override
        public boolean areItemsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meal oldItem, @NonNull Meal newItem) {
            return oldItem.getId() == newItem.getId();
        }
    }
}
