package com.example.android.bakingapp;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

import java.util.ArrayList;

public class Recipe {

    private int id;

    @SerializedName("name")
    private String text;

    private Long servings;
    private String image;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Step> steps;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}

/*
public class Ingredients {

}*/
