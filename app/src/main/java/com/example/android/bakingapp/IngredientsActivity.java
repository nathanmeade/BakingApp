package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {
    private Bundle recipeBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra("recipe");
        IngredientsFragment fragobj = new IngredientsFragment();
        fragobj.setRecipeBundle(recipeBundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredients_fragment_tag, fragobj)
                .commit();
    }
}
