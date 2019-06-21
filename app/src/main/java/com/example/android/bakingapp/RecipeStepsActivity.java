package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepAdapter.RecipeStepAdapterOnClickHandler {
    private Bundle ingredientsBundle;
    private Bundle stepsBundle;
    @BindView(R.id.steps_recycler_view)
    RecyclerView recyclerView;
    private RecipeStepAdapter recipeStepAdapter;
    private RecipeStepAdapter.RecipeStepAdapterOnClickHandler recipeStepAdapterOnClickHandler;
    private int totalSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        totalSteps = 0;
        ButterKnife.bind(this);
        recipeStepAdapterOnClickHandler = this;
//        TextView textView = findViewById(R.id.text_view);
        Intent intent = getIntent();
        String id = intent.getStringExtra("text");
        ingredientsBundle = intent.getBundleExtra("ingredients");
        stepsBundle = intent.getBundleExtra("steps");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) ingredientsBundle.getSerializable("ingredients");
/*        textView.setText(id);
        for (Ingredient ingredient: ingredients) {
            textView.append(ingredient.getIngredient());
        }*/
        initializeRecyclerView();
    }

    public void ingredientsIntent(View view){
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("ingredients", ingredientsBundle);
        startActivity(intent);
    }

/*    public void stepsIntent(View view){
        Intent intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("steps", stepsBundle);
        startActivity(intent);
    }*/

    //Account for including next and previous steps, if applicable.
    //The entire Steps bundle should probably be sent to the step details activity, just with a step id as an extra that is passed in the intent.
    //if condition can then check within the step details activity on whether it should have links to next and previous. This will also be more efficient
    //for receiving intents and extras for when the next and previous buttons are hit. All that really needs to be passed is the step id.
    //Proposal:
    //onClick(int id){
        //Intent intent = new Intent(this, RecipeStepDetailsActivity.class);
        //intent.putExtra("id", id);
        //intent.putExtra("steps", steps);
        //startActivity(intent);
    //}
    @Override
    public void onClick(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        Intent intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("shortDescription", shortDescription);
        intent.putExtra("description", description);
        intent.putExtra("videoUrl", videoUrl);
        intent.putExtra("thumbnailUrl", thumbnailUrl);
        startActivity(intent);
    }

    public void initializeRecyclerView(){
        ArrayList<Step> steps = (ArrayList<Step>) stepsBundle.getSerializable("steps");
        ArrayList<Integer> mIds = new ArrayList<>();
        ArrayList<String> mShortDescriptions = new ArrayList<>();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mVideoUrls = new ArrayList<>();
        ArrayList<String> mThumbnailUrls = new ArrayList<>();
        for (Step step : steps ){
            totalSteps++;
            mIds.add(step.getId());
            mShortDescriptions.add(step.getShortDescription());
            mDescriptions.add(step.getDescription());
            mVideoUrls.add(step.getVideoURL());
            mThumbnailUrls.add(step.getThumbnailURL());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeStepAdapter = new RecipeStepAdapter(mIds, mShortDescriptions, mDescriptions, mVideoUrls, mThumbnailUrls, recipeStepAdapterOnClickHandler);
        recyclerView.setAdapter(recipeStepAdapter);
    }
}
