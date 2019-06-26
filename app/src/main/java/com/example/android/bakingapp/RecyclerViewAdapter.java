package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewAdapterViewHolder> {
    private ArrayList<Recipe> mRecipes;
    private ArrayList<String> mTexts;
    private final RecyclerViewAdapterOnClickHandler mClickHandler;

    public interface RecyclerViewAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public RecyclerViewAdapter(ArrayList<Recipe> mRecipes, ArrayList<String> mTexts, RecyclerViewAdapterOnClickHandler mClickHandler) {
        this.mRecipes = mRecipes;
        this.mTexts = mTexts;
        this.mClickHandler = mClickHandler;
    }

    public class RecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView textView;

        public RecyclerViewAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = mRecipes.get(adapterPosition);
            mClickHandler.onClick(recipe);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        RecyclerViewAdapterViewHolder recipeViewHolder = new RecyclerViewAdapterViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewAdapterViewHolder recyclerViewAdapterViewHolder, int i) {
        recyclerViewAdapterViewHolder.textView.setText(mTexts.get(i));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}

