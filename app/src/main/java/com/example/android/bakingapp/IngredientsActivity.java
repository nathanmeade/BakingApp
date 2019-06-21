package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private Bundle ingredientsBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        ingredientsBundle = intent.getBundleExtra("ingredients");
        ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) ingredientsBundle.getSerializable("ingredients");
        TextView textView = findViewById(R.id.ingredients_text_view);
        textView.setText("");
        for (Ingredient ingredient: ingredients) {
            textView.append(ingredient.getQuantity() + " ");
            textView.append(ingredient.getMeasure() + "\t");
            textView.append(ingredient.getIngredient() + "\n\n");
        }
    }
}
