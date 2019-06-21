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
    private ArrayList<Integer> mIds;
    private ArrayList<String> mTexts;
    private ArrayList<Long> mServings;
    private ArrayList<String> mImages;
    private ArrayList<ArrayList<Ingredient>> mIngredients;
    private ArrayList<ArrayList<Step>> mSteps;
    private final RecyclerViewAdapterOnClickHandler mClickHandler;

    public interface RecyclerViewAdapterOnClickHandler {
        void onClick(int id, String text, Long serving, String image, ArrayList<Ingredient> ingredients, ArrayList<Step> steps);
    }

    public RecyclerViewAdapter(ArrayList<Integer> mIds, ArrayList<String> mTexts, ArrayList<Long> mServings, ArrayList<String> mImages, ArrayList<ArrayList<Ingredient>> mIngredients, ArrayList<ArrayList<Step>> mSteps, RecyclerViewAdapterOnClickHandler mClickHandler) {
        this.mIds = mIds;
        this.mTexts = mTexts;
        this.mServings = mServings;
        this.mImages = mImages;
        this.mIngredients = mIngredients;
        this.mSteps = mSteps;
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
            int id = mIds.get(adapterPosition);
            String text = mTexts.get(adapterPosition);
            Long serving = mServings.get(adapterPosition);
            String image = mImages.get(adapterPosition);
            ArrayList<Ingredient> ingredients = mIngredients.get(adapterPosition);
            ArrayList<Step> steps = mSteps.get(adapterPosition);
            mClickHandler.onClick(id, text, serving, image, ingredients, steps);
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
        //recyclerViewAdapterViewHolder.textView.append(mIds.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return mIds.size();
        //return 4;
    }

    public ArrayList<Integer> getmIds() {
        return mIds;
    }

    public ArrayList<String> getmTexts() {
        return mTexts;
    }

    public ArrayList<Long> getmServings() {
        return mServings;
    }

    public ArrayList<String> getmImages() {
        return mImages;
    }

    public ArrayList<ArrayList<Ingredient>> getmIngredients() {
        return mIngredients;
    }

    public ArrayList<ArrayList<Step>> getmSteps() {
        return mSteps;
    }

    public RecyclerViewAdapterOnClickHandler getmClickHandler() {
        return mClickHandler;
    }
}

