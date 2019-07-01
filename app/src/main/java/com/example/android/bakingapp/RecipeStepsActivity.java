package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class RecipeStepsActivity extends AppCompatActivity {
    private Bundle recipeBundle;
    private RecipeStepAdapter recipeStepAdapter;
    private RecipeStepAdapter.RecipeStepAdapterOnClickHandler recipeStepAdapterOnClickHandler;
    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        Intent intent = getIntent();
        String recipeString = getString(R.string.recipe);
        recipeBundle = intent.getBundleExtra(recipeString);
        Bundle idBundle = new Bundle();
        String idString = getString(R.string.id);
        idBundle.putInt(idString, 0);
        isTablet = (findViewById(R.id.tablet_layout)!=null);
        if (isTablet) {
            StepDetailsFragment fragobj = new StepDetailsFragment();
            fragobj.setIdBundle(idBundle);
            fragobj.setRecipeBundle(recipeBundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_details_fragment_tag, fragobj)
                    .commit();
        }
        Recipe recipe = (Recipe) recipeBundle.getSerializable(getString(R.string.recipe));
        String recipeName = recipe.getText();
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        Intent backIntent = new Intent(this, MainActivity.class);
        toolbar.setTitle(recipeName);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                startActivity(backIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra(getString(R.string.recipe));
        if(recipeBundle!=null){
            updateWidget(recipeBundle);
            createFragment();
        }
    }

    private void createFragment(){
        RecipeStepsFragment fragobj = new RecipeStepsFragment();
        fragobj.setRecipeBundle(recipeBundle);
        fragobj.setIsTablet(isTablet);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_steps_activity_fragment_tag, fragobj)
                .commit();
    }

    public void updateWidget(Bundle bundle){
        Recipe recipe = (Recipe) recipeBundle.getSerializable(getString(R.string.recipe));
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        String ingredientsString = "";
        StringBuilder s = new StringBuilder(100);
        for (Ingredient ingredient: ingredients) {
            s.append(ingredient.getQuantity() + " ");
            s.append(ingredient.getMeasure() + "\t");
            s.append(ingredient.getIngredient() + "\n\n");
        }
        ingredientsString = s.toString();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplication());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getApplication(), ExampleAppWidgetProvider.class));
        //Now update all widgets
        String string = "macewindu";
        ExampleAppWidgetProvider.updateWidgets(getApplication(), appWidgetManager, appWidgetIds, ingredientsString);
    }
}
