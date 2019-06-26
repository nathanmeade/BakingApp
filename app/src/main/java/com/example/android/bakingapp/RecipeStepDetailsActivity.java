package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;

public class RecipeStepDetailsActivity extends AppCompatActivity {
    private Bundle stepsBundle;
    private Bundle recipeBundle;
    private TextView textView;
    private SimpleExoPlayer player;
    private PlayerView playerView;
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
        textView = findViewById(R.id.steps_text_view);
        id = intent.getIntExtra("id", 0);
        Bundle idBundle = new Bundle();
        idBundle.putInt("id", id);
        stepsBundle = intent.getBundleExtra("steps");
        recipeBundle = intent.getBundleExtra("recipe");

        ArrayList<Step> steps = (ArrayList<Step>) stepsBundle.getSerializable("steps");
        ArrayList<String> mShortDescriptions = new ArrayList<>();
        size = steps.size();
        ArrayList<String> mDescriptions = new ArrayList<>();
        ArrayList<String> mVideoUrls = new ArrayList<>();
        for (Step step : steps ){
            mShortDescriptions.add(step.getShortDescription());
            mDescriptions.add(step.getDescription());
            mVideoUrls.add(step.getVideoURL());
        }
        String shortDescription = mShortDescriptions.get(id);
        String description = mDescriptions.get(id);
        String videoUrl = mVideoUrls.get(id);
        playerView = findViewById(R.id.player_view);
        nextAndPreviousSteps();
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
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
        fragobj.setStepsBundle(stepsBundle);
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
        intent.putExtra("steps", stepsBundle);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }
}
