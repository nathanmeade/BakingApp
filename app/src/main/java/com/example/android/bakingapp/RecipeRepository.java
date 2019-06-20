package com.example.android.bakingapp;

import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeRepository {

    private String content;
    private String contentToReturn;

    private static final String LOG_TAG = "nathanTest";

    public String getJsonParsed(){
        //contentToReturn = "";
        Log.d(LOG_TAG, "start of getJsonParsed");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://d17h27t6h515a5.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Recipe>> call = jsonPlaceHolderApi.getRecipe();

        Log.d(LOG_TAG, "before enqueue");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {

                if (!response.isSuccessful()){
                    //textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Recipe> recipes = response.body();

                for (Recipe recipe : recipes) {
                    content = "";
                    content += "ID: " + recipe.getId() + "\n";
                    //Log.d(LOG_TAG, recipe.getText());
                    Log.d(LOG_TAG, content);
                    content += "Text: " + recipe.getText() + "\n";
                    content += "Servings: " + recipe.getServings() + "\n";
                    content += "Image: " + recipe.getImage() + "\n\n";
/*                    JSONArray jsonArray = recipe.getIngredients();
                    try {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                        content += jsonObject.get("measure");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                    content += "Ingredients:\n";
                    ArrayList<Ingredient> ingredients = recipe.getIngredients();
                    for (Ingredient ingredient : ingredients ){
                        content += "Quantity: " + ingredient.getQuantity() + "\n";
                        content += "Measure: " + ingredient.getMeasure() + "\n";
                        content += "Ingredient: " + ingredient.getIngredient() + "\n\n";
                    }

                    content += "Steps:\n";
                    ArrayList<Step> steps = recipe.getSteps();
                    for (Step step : steps ){
                        content += "ID: " + step.getId() + "\n";
                        content += "Short Description: " + step.getShortDescription() + "\n";
                        content += "Description: " + step.getDescription() + "\n";
                        content += "Video URL: " + step.getVideoURL() + "\n";
                        content += "Thumbnail: " + step.getThumbnailURL() + "\n\n";
                    }
                    contentToReturn += content;
                    //Log.d(LOG_TAG, "contentToReturn: " + contentToReturn);
                    //textViewResult.append(content);
                }

                //Log.d(LOG_TAG, "contentToReturn: " + contentToReturn);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
            }
        });
        Log.d(LOG_TAG, "contentToReturn: " + contentToReturn);
        return contentToReturn;
    }

}

