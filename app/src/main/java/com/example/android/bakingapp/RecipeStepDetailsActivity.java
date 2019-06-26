package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecipeStepDetailsActivity extends AppCompatActivity {
    private Bundle recipeBundle;
    private Button previousButton;
    private Button nextButton;
    private Intent intent;
    private Boolean previousButtonBoolean;
    private Boolean nextButtonBoolean;
    private int id;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);
        Intent intent = getIntent();
        previousButton = findViewById(R.id.previous_button);
        nextButton = findViewById(R.id.next_button);
        id = intent.getIntExtra("id", 0);
        Bundle idBundle = new Bundle();
        idBundle.putInt("id", id);
        recipeBundle = intent.getBundleExtra("recipe");
        Recipe recipe = (Recipe) recipeBundle.getSerializable("recipe");
        ArrayList<Step> steps = recipe.getSteps();
        size = steps.size();
        nextAndPreviousSteps();
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        Intent backIntent = new Intent(this, RecipeStepsActivity.class);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                backIntent.putExtra("recipe", recipeBundle);
                startActivity(backIntent);
            }
        });
        StepDetailsFragment fragobj = new StepDetailsFragment();
        fragobj.setIdBundle(idBundle);
        fragobj.setRecipeBundle(recipeBundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_details_fragment_tag, fragobj)
                .commit();
    }

    private void nextAndPreviousSteps(){
        if ((id > 0)&& id<(size - 1)) {
            //previous step true
            previousButtonBoolean = true;
            //next step true
            nextButtonBoolean = true;
        }
        else if (id > 0){
            //previous step true
            nextButton.setVisibility(View.GONE);
            nextButtonBoolean = false;
            previousButtonBoolean = true;
        }
        else {
            //next step true
            previousButton.setVisibility(View.GONE);
            previousButtonBoolean = false;
            nextButtonBoolean = true;
        }

    }

    public void previousButton(View view){
        if (previousButtonBoolean) {
            stepIntent((id-1));
        }
    }

    public void nextButton(View view){
        if (nextButtonBoolean) {
            stepIntent((id+1));
        }
    }

    private void stepIntent(int step){
        intent = new Intent(this, RecipeStepDetailsActivity.class);
        intent.putExtra("id", step);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }
}
