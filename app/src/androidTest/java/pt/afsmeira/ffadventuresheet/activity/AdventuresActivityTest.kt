package pt.afsmeira.ffadventuresheet.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class AdventuresActivityTest {

    @Rule
    @JvmField
    var adventuresActivityRule = ActivityTestRule(AdventuresActivity::class.java)

    /**
     * Basic test to check if the activity loaded as expected.
     */
    @Test
    fun activityLoadsTest() {
        val titleBar = onView(withText(R.string.app_name))
        titleBar.check(matches(isDisplayed()))

        val adventuresList = onView(withId(R.id.activity_adventures_list))
        adventuresList.check(matches(isDisplayed()))

        val newAdventureButton = onView(withId(R.id.activity_adventures_new_adventure))
        newAdventureButton.check(matches(isDisplayed()))
    }

    /**
     * Test that [NewAdventureActivity] is opened after a button press.
     */
    @Test
    fun openNewAdventureActivityTest() {
        Intents.init()

        val newAdventureButton = onView(withId(R.id.activity_adventures_new_adventure))
            .check(matches(isDisplayed()))
        newAdventureButton.perform(click())

        intended(hasComponent(NewAdventureActivity::class.java.name))

        Intents.release()
    }
}
