package com.example.android.bakingapp;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeStepsActivityTest {

    @Rule
    public IntentsTestRule<RecipeStepsActivity> mActivityRule = new IntentsTestRule<>(
            RecipeStepsActivity.class);

    @Test
    public void clickBackArrowButton(){
        onView(withId(R.id.my_awesome_toolbar)).perform(click());
    }
}
