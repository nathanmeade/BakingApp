package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeStepsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        TextView textView = findViewById(R.id.text_view);
        Intent intent = getIntent();
        String id = intent.getStringExtra("text");
        Bundle bundle = intent.getBundleExtra("ingredients");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) bundle.getSerializable("ingredients");
        textView.setText(id);
        for (Ingredient ingredient: ingredients) {
            textView.append(ingredient.getIngredient());
        }
    }
}
