package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        recipeBundle = intent.getBundleExtra("recipe");
        Bundle idBundle = new Bundle();
        idBundle.putInt("id", 0);
        isTablet = (findViewById(R.id.tablet_layout)!=null);
        if (isTablet){
            StepDetailsFragment fragobj = new StepDetailsFragment();
            fragobj.setIdBundle(idBundle);
            fragobj.setRecipeBundle(recipeBundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_details_fragment_tag, fragobj)
                    .commit();
/*            IngredientsFragment fragobj = new IngredientsFragment();
            fragobj.setRecipeBundle(recipeBundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_details_fragment_tag, fragobj)
                    .commit();*/
        }
        //recipeStepAdapterOnClickHandler = this;
        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        Intent backIntent = new Intent(this, MainActivity.class);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                //backIntent.putExtra("recipe", recipeBundle);
                startActivity(backIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        recipeBundle = intent.getBundleExtra("recipe");
        //should be create fragment instead
        if(recipeBundle!=null){
            updateWidget();
            createFragment();
        }

        //initializeRecyclerView();
    }

    private void createFragment(){
        RecipeStepsFragment fragobj = new RecipeStepsFragment();
        fragobj.setRecipeBundle(recipeBundle);
        fragobj.setIsTablet(isTablet);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.recipe_steps_activity_fragment_tag, fragobj)
                .commit();
    }

    public void updateWidget(){
/*        Intent intent = new Intent(this, ExampleAppWidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), ExampleAppWidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);*/

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplication());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getApplication(), ExampleAppWidgetProvider.class));
        //Now update all widgets
        String string = "macewindu";
        //string = editTextBox.getText().toString();
        ExampleAppWidgetProvider.updatePlantWidgets(getApplication(), appWidgetManager, appWidgetIds, string);
    }
}
