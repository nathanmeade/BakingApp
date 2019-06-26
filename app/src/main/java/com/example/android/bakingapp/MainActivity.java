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
                ArrayList<Recipe> mRecipes = new ArrayList<>();
                ArrayList<String> mTexts = new ArrayList<>();
                if (!response.isSuccessful()){
                    return;
                }
                List<Recipe> recipes = response.body();
                for (Recipe recipe : recipes) {
                    mRecipes.add(recipe);
                    mTexts.add(recipe.getText());
                }
                recyclerViewAdapter = new RecyclerViewAdapter(mRecipes, mTexts, clickHandler);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent intent = new Intent(this, RecipeStepsActivity.class);
        Bundle recipeBundle = new Bundle();
        recipeBundle.putSerializable("recipe", (Serializable) recipe);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }
}