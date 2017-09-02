package com.nenoproject.smokybakers;

/**
 * Created by sadanandk on 8/28/2017.
 */

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


// Tests for MainActivity
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {

    // Preferred JUnit 4 mechanism of specifying the activity to be launched before each test
    @Rule
    public ActivityTestRule<HomeActivity> activityTestRule =
            new ActivityTestRule<>(HomeActivity.class);

    // Looks for an EditText with id = "R.id.etInput"
    // Types the text "Hello" into the EditText
    // Verifies the EditText has text "Hello"
   /* @Test
    public void validateEditText() {
       // onView(withId(R.id.etInput)).perform(typeText("Hello")).check(matches(withText("Hello")));
       // onView(withId(R.id.lvFoodItem))
             //   .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

    }*/
    @Test
    public void checkRecycleView()
    {
        // Click on the RecyclerView item at position 2
        onView(withId(R.id.lvFoodItem))
                .check(matches(hasDescendant(withText("Nutella Pie"))));


    }

}