package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeStepAdapter extends RecyclerView.Adapter<RecipeStepAdapter.RecipeStepAdapterViewHolder> {
    private ArrayList<Integer> mIds;
    private ArrayList<String> mShortDescriptions;
    private ArrayList<String> mDescriptions;
    private ArrayList<String> mVideoUrls;
    private ArrayList<String> mThumbnailUrls;
    private final RecipeStepAdapterOnClickHandler mClickHandler;

    public interface RecipeStepAdapterOnClickHandler {
        void onClick(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl);
    }

    public RecipeStepAdapter(ArrayList<Integer> mIds, ArrayList<String> mShortDescriptions, ArrayList<String> mDescriptions, ArrayList<String> mVideoUrls, ArrayList<String> mThumbnailUrls, RecipeStepAdapterOnClickHandler mClickHandler) {
        this.mIds = mIds;
        this.mShortDescriptions = mShortDescriptions;
        this.mDescriptions = mDescriptions;
        this.mVideoUrls = mVideoUrls;
        this.mThumbnailUrls = mThumbnailUrls;
        this.mClickHandler = mClickHandler;
    }

    public class RecipeStepAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView textView;

        public RecipeStepAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_step_list_item);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            int id = mIds.get(adapterPosition);
            String shortDescription = mShortDescriptions.get(adapterPosition);
            String description = mDescriptions.get(adapterPosition);
            String videoUrl = mVideoUrls.get(adapterPosition);
            String thumbnailUrl = mThumbnailUrls.get(adapterPosition);
            mClickHandler.onClick(id, shortDescription, description, videoUrl, thumbnailUrl);
        }
    }

    @NonNull
    @Override
    public RecipeStepAdapter.RecipeStepAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.step_list_item, viewGroup, false);
        RecipeStepAdapterViewHolder recipeStepViewHolder = new RecipeStepAdapterViewHolder(view);
        return recipeStepViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepAdapter.RecipeStepAdapterViewHolder recipeStepAdapterViewHolder, int i) {
        if (mShortDescriptions.get(i).equals("Recipe Introduction")){
            recipeStepAdapterViewHolder.textView.setText(mShortDescriptions.get(i));
        }
        else {
            recipeStepAdapterViewHolder.textView.setText("Step " + mIds.get(i) + ": " + mShortDescriptions.get(i));
        }

        //recipeStepAdapterViewHolder.textView.append(mIds.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return mIds.size();
    }
}
