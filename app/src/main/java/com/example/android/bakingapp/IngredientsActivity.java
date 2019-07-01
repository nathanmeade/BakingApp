package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class IngredientsActivity extends AppCompatActivity {
    private Bundle recipeBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra(getString(R.string.recipe));
        IngredientsFragment fragobj = new IngredientsFragment();
        fragobj.setRecipeBundle(recipeBundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ingredients_fragment_tag, fragobj)
                .commit();
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        Intent backIntent = new Intent(this, RecipeStepsActivity.class);
        toolbar.setTitle(R.string.ingredientsLabel);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                backIntent.putExtra(getString(R.string.recipe), recipeBundle);
                startActivity(backIntent);
            }
        });
    }
}
