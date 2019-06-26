package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeStepsActivity extends AppCompatActivity implements RecipeStepAdapter.RecipeStepAdapterOnClickHandler {
    private Bundle recipeBundle;
    @BindView(R.id.steps_recycler_view)
    RecyclerView recyclerView;
    private RecipeStepAdapter recipeStepAdapter;
    private RecipeStepAdapter.RecipeStepAdapterOnClickHandler recipeStepAdapterOnClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        ButterKnife.bind(this);
        recipeStepAdapterOnClickHandler = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra("recipe");
        initializeRecyclerView();
    }

    public void ingredientsIntent(View view){
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }

    public void initializeRecyclerView(){
        Recipe recipe = (Recipe) recipeBundle.getSerializable("recipe");
        ArrayList<Step> steps = recipe.getSteps();
                ArrayList<Integer> mIds = new ArrayList<>();
        ArrayList<String> mShortDescriptions = new ArrayList<>();
        for (Step step : steps ){
            mIds.add(step.getId());
            mShortDescriptions.add(step.getShortDescription());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeStepAdapter = new RecipeStepAdapter(mIds, mShortDescriptions, recipeStepAdapterOnClickHandler);
        recyclerView.setAdapter(recipeStepAdapter);
    }
}
