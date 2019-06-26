package com.example.android.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeStepsFragment extends Fragment implements RecipeStepAdapter.RecipeStepAdapterOnClickHandler {
    private Bundle recipeBundle;
    RecyclerView recyclerView;
    private RecipeStepAdapter recipeStepAdapter;
    private RecipeStepAdapter.RecipeStepAdapterOnClickHandler recipeStepAdapterOnClickHandler;
    private boolean isTablet;

    private OnFragmentInteractionListener mListener;

    //public interface On

    public RecipeStepsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        recyclerView = rootView.findViewById(R.id.steps_recycler_view);
        recipeStepAdapterOnClickHandler = this::onClick;
        initializeRecyclerView();
        TextView textView = rootView.findViewById(R.id.ingredients_intent);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet){
                    //need to change:
                    ingredientsIntent();
                }
                else {
                    ingredientsIntent();
                }
            }
        });

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

    public void ingredientsIntent(){
        Intent intent = new Intent(getContext(), IngredientsActivity.class);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }

    @Override
    public void onClick(int id) {
        Intent intent = new Intent(getContext(), RecipeStepDetailsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("recipe", recipeBundle);
        startActivity(intent);
    }

    public void initializeRecyclerView(){
        Recipe recipe = (Recipe) recipeBundle.getSerializable("recipe");
        ArrayList<Step> steps = recipe.getSteps();
        ArrayList<Integer> mIds = new ArrayList<>();
        ArrayList<String> mShortDescriptions = new ArrayList<>();
        for (Step step : steps ){
            mIds.add(step.getId());
            mShortDescriptions.add(step.getShortDescription());
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recipeStepAdapter = new RecipeStepAdapter(mIds, mShortDescriptions, recipeStepAdapterOnClickHandler);
        recyclerView.setAdapter(recipeStepAdapter);
    }

    public void setRecipeBundle(Bundle bundle){
        recipeBundle = bundle;
    }

    public void setIsTablet(boolean b){
        isTablet = b;
    }
}
