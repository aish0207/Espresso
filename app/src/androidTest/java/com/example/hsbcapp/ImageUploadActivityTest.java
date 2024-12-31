package com.example.hsbcapp;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ImageUploadActivityTest {

    @Before
    public void setUp() {
        // Initialize Intents for Espresso
        Intents.init();
    }

    @Test
    public void testImageUploadActivityLaunch() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Simulate clicking the "Add Image" button
            Espresso.onView(withId(R.id.add_image_button))
                    .perform(ViewActions.click());

            // Verify that the ImageUploadActivity is launched
            intended(IntentMatchers.hasComponent(ImageUploadActivity.class.getName()));
        }
    }

    @Test
    public void testFormSubmission() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Input sample data into the form fields
            Espresso.onView(withId(R.id.namef)).perform(ViewActions.typeText("John Doe"));
            Espresso.onView(withId(R.id.addressf)).perform(ViewActions.typeText("123 Main St"));
            Espresso.onView(withId(R.id.mobilef)).perform(ViewActions.typeText("9876543210"));
            Espresso.closeSoftKeyboard();

            // Click the Submit button
            Espresso.onView(withId(R.id.submit_button)).perform(ViewActions.click());

            // Verify that the form data is filled correctly
            Espresso.onView(withId(R.id.namef)).check(ViewAssertions.matches(ViewMatchers.withText("John Doe")));
            Espresso.onView(withId(R.id.addressf)).check(ViewAssertions.matches(ViewMatchers.withText("123 Main St")));
            Espresso.onView(withId(R.id.mobilef)).check(ViewAssertions.matches(ViewMatchers.withText("9876543210")));
        }
    }

    @Test
    public void testImageSelection() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Check if the ImageView for the selected image is displayed
            Espresso.onView(withId(R.id.selected_image_view)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }
    }

    @Test
    public void testViewAllButton() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Simulate clicking the "View All" button
            Espresso.onView(withId(R.id.view_all_button)).perform(ViewActions.click());

            // Verify that the TableActivity is launched
            intended(IntentMatchers.hasComponent(TableActivity.class.getName()));
        }
    }

    @Test
    public void testCameraPermission() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Simulate clicking the "Add Image" button
            Espresso.onView(withId(R.id.add_image_button)).perform(ViewActions.click());

            // Normally, you need to check if the camera permission is requested.
            // You could mock the permission request process or add assertions to verify permission flow.
        }
    }

    @Test
    public void testGalleryPermission() {
        // Launch the ImageUploadActivity using ActivityScenario
        try (ActivityScenario<ImageUploadActivity> scenario = ActivityScenario.launch(ImageUploadActivity.class)) {
            // Simulate clicking the "Add Image" button to check gallery permission
            Espresso.onView(withId(R.id.add_image_button)).perform(ViewActions.click());

            // Similar to camera permission, check if the gallery permission request happens here.
        }
    }

    @After
    public void tearDown() {
        // Release Intents after tests are completed
        Intents.release();
    }
}
