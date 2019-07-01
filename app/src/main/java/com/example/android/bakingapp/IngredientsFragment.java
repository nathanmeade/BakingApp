package com.example.android.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class IngredientsFragment extends Fragment {
    private Bundle recipeBundle;

    private OnFragmentInteractionListener mListener;

    public IngredientsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        Recipe recipe = (Recipe) recipeBundle.getSerializable(getString(R.string.recipe));
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        TextView textView = rootView.findViewById(R.id.ingredients_text_view);
        textView.setText("");
        for (Ingredient ingredient: ingredients) {
            textView.append(ingredient.getQuantity() + " ");
            textView.append(ingredient.getMeasure() + "\t");
            textView.append(ingredient.getIngredient() + "\n\n");
        }
        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setRecipeBundle(Bundle bundle){
        recipeBundle = bundle;
    }
}
