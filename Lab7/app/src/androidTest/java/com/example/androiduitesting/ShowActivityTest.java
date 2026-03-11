package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testActivitySwitch() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city in the list
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if ShowActivity is displayed by checking for its unique TextView
        onView(withId(R.id.textView_city_name)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameConsistency() {
        String cityName = "Vancouver";
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(cityName));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Check if the city name is consistent in ShowActivity
        onView(withId(R.id.textView_city_name)).check(matches(withText(cityName)));
    }

    @Test
    public void testBackButton() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText("Moscow"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the city
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // Click the back button in ShowActivity
        onView(withId(R.id.button_back)).perform(click());

        // Check if we are back in MainActivity (city_list should be visible)
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}