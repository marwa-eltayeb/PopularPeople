package com.marwaeltayeb.popularpeople

import android.content.ComponentName
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.marwaeltayeb.popularpeople.view.DetailsActivity
import com.marwaeltayeb.popularpeople.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RecyclerViewTest {

    @get:Rule
    var activityRule: IntentsTestRule <MainActivity> = IntentsTestRule (MainActivity::class.java)

    @Test
    fun clickItem() {

        // Wait until data is loaded
        SystemClock.sleep(500)

        // Click on first item
        onView(withId(R.id.rcActorsList))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // Check if the correct activity is opened
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        intended(hasComponent(ComponentName(context, DetailsActivity::class.java)))
    }
}