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

public class RecipeStepsActivity extends AppCompatActivity {
    private Bundle recipeBundle;
    private RecipeStepAdapter recipeStepAdapter;
    private RecipeStepAdapter.RecipeStepAdapterOnClickHandler recipeStepAdapterOnClickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);

        //recipeStepAdapterOnClickHandler = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra("recipe");
        //should be create fragment instead
        createFragment();
        //initializeRecyclerView();
    }

    private void createFragment(){
        RecipeStepsFragment fragobj = new RecipeStepsFragment();
        fragobj.setRecipeBundle(recipeBundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_steps_activity_fragment_tag, fragobj)
                .commit();
    }
}
