package pt.afsmeira.ffadventuresheet.ui.activity

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.util.AssertionUtils
import pt.afsmeira.ffadventuresheet.util.FFAdventureSheetDatabaseRule
import pt.afsmeira.ffadventuresheet.util.WithIdlingResources

@LargeTest
@RunWith(AndroidJUnit4::class)
class NewAdventureActivityTest : WithIdlingResources {

    @Rule
    @JvmField
    var newAdventureActivityRuleChain: RuleChain =
        RuleChain
            .outerRule(FFAdventureSheetDatabaseRule())
            .around(ActivityTestRule(NewAdventureActivity::class.java))

    private val firstBookName = "The Warlock of Firetop Mountain"

    /**
     * Basic test to check if the activity loaded as expected.
     */
    @Test
    fun activityLoadsTest() {
        val titleBar = onView(withText(R.string.activity_new_adventure_title))
        titleBar.check(matches(isDisplayed()))

        val bookGrid = onView(withId(R.id.activity_new_adventure_book_grid))
        bookGrid.check(matches(isDisplayed()))
        bookGrid.check(AssertionUtils.recyclerViewHasItemCount(69))

        val book = onView(withText(firstBookName))
        book.check(matches(isDisplayed()))
    }

    /**
     * Test that creating a new adventure using the dialog works.
     */
    @Test
    fun newAdventureDialogTest() {
        Intents.init()

        val book = onView(withText(firstBookName)).check(matches(isDisplayed()))
        book.perform(click())

        val dialogText = getApplicationContext<Context>()
            .resources
            .getString(R.string.dialog_new_adventure_message, firstBookName)

        val newAdventureDialog = onView(withText(dialogText)).inRoot(isDialog())
        newAdventureDialog.check(matches(isDisplayed()))

        val okButton = onView(withId(android.R.id.button1))
        okButton.perform(click())

        Intents.intended(hasComponent(NewCharacterActivity::class.java.name))
        Intents.intended(hasExtraWithKey(NewCharacterActivity.BOOK_INTENT_KEY))
        Intents.release()
    }
}
