package org.digitalsoft.myapplication;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testMainFlow() {
        disableAnimations();

        onView(withId(R.id.button))
                .check(matches(isEnabled()));

        onView(withId(R.id.button))
                .perform(click());

        onView(withId(R.id.button))
                .check(matches(not(isEnabled())));

    }

    private void disableAnimations() {
        try {
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                    .executeShellCommand("settings put global transition_animation_scale 0");
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                    .executeShellCommand("settings put global window_animation_scale 0");
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                    .executeShellCommand("settings put global animator_duration_scale 0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}