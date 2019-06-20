package com.example.android.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.RecyclerViewAdapterOnClickHandler {
    private static final String LOG_TAG = "nathanTest";
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerViewAdapter.RecyclerViewAdapterOnClickHandler clickHandler;
    @BindString(R.string.base_url) String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        clickHandler = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getJsonParsed();
    }

    public void getJsonParsed(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Recipe>> call = jsonPlaceHolderApi.getRecipe();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                ArrayList<Integer> mIds = new ArrayList<>();
                ArrayList<String> mTexts = new ArrayList<>();
                ArrayList<Long> mServings = new ArrayList<>();
                ArrayList<String> mImages = new ArrayList<>();
                ArrayList<ArrayList<Ingredient>> mIngredients = new ArrayList<>();
                ArrayList<ArrayList<Step>> mSteps = new ArrayList<>();
                if (!response.isSuccessful()){
                    return;
                }
                List<Recipe> recipes = response.body();
                for (Recipe recipe : recipes) {
                    mIds.add(recipe.getId());
                    mTexts.add(recipe.getText());
                    mServings.add(recipe.getServings());
                    mImages.add(recipe.getImage());
                    ArrayList<Ingredient> ingredients = recipe.getIngredients();
                    mIngredients.add(ingredients);
                    ArrayList<Step> steps = recipe.getSteps();
                    mSteps.add(steps);
                }
                recyclerViewAdapter = new RecyclerViewAdapter(mIds, mTexts, mServings, mImages, mIngredients, mSteps, clickHandler);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(int id, String text, Long serving, String image, ArrayList<Ingredient> ingredients, ArrayList<Step> steps) {
        Intent intent = new Intent(this, RecipeStepsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("text", text);
        intent.putExtra("serving", serving);
        intent.putExtra("image", image);
        //how to put extra for arraylists?
        Bundle ingredientsBundle = new Bundle();
        //Bundle stepsBundle = new Bundle();
        ingredientsBundle.putSerializable("ingredients", (Serializable) ingredients);
        //stepsBundle.putSerializable("steps", steps);
        intent.putExtra("ingredients", ingredientsBundle);
        //intent.putExtra("steps", stepsBundle);
        startActivity(intent);
    }
}