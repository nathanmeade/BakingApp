package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeStepsActivity extends AppCompatActivity {
    private Bundle ingredientsBundle;
    private Bundle stepsBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        TextView textView = findViewById(R.id.text_view);
        Intent intent = getIntent();
        String id = intent.getStringExtra("text");
        ingredientsBundle = intent.getBundleExtra("ingredients");
        stepsBundle = intent.getBundleExtra("steps");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) ingredientsBundle.getSerializable("ingredients");
        textView.setText(id);
        for (Ingredient ingredient: ingredients) {
            textView.append(ingredient.getIngredient());
        }
    }

    public void ingredientsIntent(View view){
        Intent intent = new Intent(this, IngredientsActivity.class);
        intent.putExtra("ingredients", ingredientsBundle);
        startActivity(intent);
    }

    public void stepsIntent(View view){
        Intent intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("steps", stepsBundle);
        startActivity(intent);
    }
}
