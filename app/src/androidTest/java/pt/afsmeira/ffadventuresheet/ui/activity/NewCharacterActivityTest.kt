package pt.afsmeira.ffadventuresheet.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import pt.afsmeira.ffadventuresheet.R
import pt.afsmeira.ffadventuresheet.model.Book
import pt.afsmeira.ffadventuresheet.model.Stat
import pt.afsmeira.ffadventuresheet.util.AssertionUtils
import pt.afsmeira.ffadventuresheet.util.AssertionUtils.firstMatching
import pt.afsmeira.ffadventuresheet.util.FFAdventureSheetDatabaseRule
import pt.afsmeira.ffadventuresheet.util.WithIdlingResources
import pt.afsmeira.ffadventuresheet.util.observeAndAwait

@LargeTest
@RunWith(AndroidJUnit4::class)
class NewCharacterActivityTest : WithIdlingResources {

    var newCharacterActivityRule =
        ActivityTestRule(NewCharacterActivity::class.java, true, false)

    var dbRule = FFAdventureSheetDatabaseRule()

    @Rule
    @JvmField
    var newCharacterActivityRuleChain: RuleChain =
        RuleChain
            .outerRule(dbRule)
            .around(newCharacterActivityRule)
            .around(InstantTaskExecutorRule())

    private fun launchActivityWithIntent(book: Book) {
        val intent = Intent().apply {
            putExtra(NewCharacterActivity.BOOK_INTENT_KEY, Gson().toJson(book))
        }
        newCharacterActivityRule.launchActivity(intent)
    }

    private fun activityWithSimpleStats() {
        val book = Book(id = 1, name = "The Warlock of Firetop Mountain", coverUrl = "cover url")
        launchActivityWithIntent(book)
    }

    private fun activityWithSingleOptionStats() {
        val book = Book(id = 17, name = "Appointment with F.E.A.R.", coverUrl = "cover url")
        launchActivityWithIntent(book)
    }

    private fun activityWithMultipleOptionSelectableStats() {
        val book = Book(id = 14, name = "Temple of Terror", coverUrl = "cover url")
        launchActivityWithIntent(book)
    }

    private fun activityWithMultipleOptionRepeatableStats() {
        val book = Book(id = 12, name = "Space Assassin", coverUrl = "cover url")
        launchActivityWithIntent(book)
    }

    @Test
    fun activityLoadsTest() {
        activityWithSimpleStats()

        val actionBar = onView(withText(R.string.activity_new_character_title))
        actionBar.check(matches(isDisplayed()))

        val createCharacter = onView(withText(R.string.activity_new_character_create))
        createCharacter.check(matches(isDisplayed()))

        val statList = onView(withId(R.id.activity_new_character_stat_list))
        statList.check(matches(isDisplayed()))
        statList.check(AssertionUtils.recyclerViewHasItemCountGreaterThan(0))
    }

    @Test
    fun intStatTest() {
        activityWithSimpleStats()

        // See caveats of firstMatching

        // Check for controls of IntStatView
        onView(firstMatching(withId(R.id.view_stat_int_add_btn))).check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_int_subtract_btn))).check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_int_name))).check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_int_value))).check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_int_value))).check(matches(withText("0")))

        // Click subtract button and check stat value
        onView(firstMatching(withId(R.id.view_stat_int_subtract_btn))).perform(click())
        onView(firstMatching(withId(R.id.view_stat_int_value))).check(matches(withText("0")))

        // Click add button and check stat value
        onView(firstMatching(withId(R.id.view_stat_int_add_btn))).perform(click())
        onView(firstMatching(withId(R.id.view_stat_int_value))).check(matches(withText("1")))
        onView(firstMatching(withId(R.id.view_stat_int_add_btn))).perform(click())
        onView(firstMatching(withId(R.id.view_stat_int_value))).check(matches(withText("2")))

        // Create Adventure
        onView(withText(R.string.activity_new_character_create)).perform(click())

        // Assert activity finished with expected result
        assertThat(newCharacterActivityRule.activityResult.resultCode, `is`(Activity.RESULT_OK))

        // Assert that the adventure was created correctly
        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(1))

        val adventureStats = runBlocking {
            dbRule.db.statDao().listForAdventure(adventures[0].adventure.id)
        }
        val expectedStatValue = Stat.Value.Single.Integer(2)
        assertThat(
            adventureStats
                .map { it.toTyped() }
                .filter { it.typedStat.type == Stat.Type.INT }
                .count {
                    it.typedStat.currentValue == expectedStatValue &&
                            it.typedStat.initialValue == expectedStatValue
                },
            `is`(1)
        )
    }

    @Test
    fun textStatTest() {
        activityWithSimpleStats()

        // See caveats of firstMatching

        // Check for controls of TextStatView
        onView(firstMatching(withId(R.id.view_stat_text_name))).check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_text_value))).check(matches(isDisplayed()))

        // Set value of TextStatView
        onView(firstMatching(withId(R.id.view_stat_text_value))).perform(click())
        onView(firstMatching(withId(R.id.view_stat_text_value)))
            .perform(typeText("THIS IS A TEST"))

        // Create Adventure
        onView(withText(R.string.activity_new_character_create)).perform(click())

        // Assert activity finished with expected result
        assertThat(newCharacterActivityRule.activityResult.resultCode, `is`(Activity.RESULT_OK))

        // Assert that the adventure was created correctly
        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(1))

        val adventureStats = runBlocking {
            dbRule.db.statDao().listForAdventure(adventures[0].adventure.id)
        }
        val expectedStatValue = Stat.Value.Single.Text("THIS IS A TEST")
        assertThat(
            adventureStats
                .map { it.toTyped() }
                .filter { it.typedStat.type == Stat.Type.TEXT }
                .count {
                    it.typedStat.currentValue == expectedStatValue &&
                            it.typedStat.initialValue == expectedStatValue
                },
            `is`(1)
        )
    }

    @Test
    fun singleOptionStatTest() {
        activityWithSingleOptionStats()

        // Check for controls of SingleOptionStatView
        onView(withId(R.id.view_stat_single_option_name)).check(matches(isDisplayed()))
        onView(withId(R.id.view_stat_single_option_values)).check(matches(isDisplayed()))

        // Set value of SingleOptionStat
        onView(withId(R.id.view_stat_single_option_values)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)))).atPosition(2).perform(click())

        // Create Adventure
        onView(withText(R.string.activity_new_character_create)).perform(click())

        // Assert activity finished with expected result
        assertThat(newCharacterActivityRule.activityResult.resultCode, `is`(Activity.RESULT_OK))

        // Assert that the adventure was created correctly
        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(1))

        val adventureStats = runBlocking {
            dbRule.db.statDao().listForAdventure(adventures[0].adventure.id)
        }
        val expectedStatValue = Stat.Value.Single.Text("Enhanced Technological Skill")
        assertThat(
            adventureStats
                .map { it.toTyped() }
                .filter { it.typedStat.type == Stat.Type.SINGLE_OPTION }
                .count {
                    it.typedStat.currentValue == expectedStatValue &&
                            it.typedStat.initialValue == expectedStatValue
                },
            `is`(1)
        )
    }

    @Test
    fun multipleOptionSelectableStatTest() {
        activityWithMultipleOptionSelectableStats()

        // Check for controls of MultipleOptionStatBooleanView
        onView(withId(R.id.view_stat_multi_option_name)).check(matches(isDisplayed()))
        onView(withId(R.id.view_stat_multi_option_values_recycler)).check(matches(isDisplayed()))

        // See caveats of firstMatching

        // Select first option of MultipleOptionStatBooleanView
        onView(firstMatching(withId(R.id.view_stat_multi_option_boolean_option)))
            .check(matches(isDisplayed()))
        onView(firstMatching(withId(R.id.view_stat_multi_option_boolean_option))).perform(click())

        // Create Adventure
        onView(withText(R.string.activity_new_character_create)).perform(click())

        // Assert activity finished with expected result
        assertThat(newCharacterActivityRule.activityResult.resultCode, `is`(Activity.RESULT_OK))

        // Assert that the adventure was created correctly
        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(1))

        val adventureStats = runBlocking {
            dbRule.db.statDao().listForAdventure(adventures[0].adventure.id)
        }
        val expectedStatValue = Stat.Value.Multiple(
            listOf(
                Stat.Value.Multiple.Option.Selectable("Open Door", true),
                Stat.Value.Multiple.Option.Selectable("Creature Sleep", false),
                Stat.Value.Multiple.Option.Selectable("Magic Arrow", false),
                Stat.Value.Multiple.Option.Selectable("Language", false),
                Stat.Value.Multiple.Option.Selectable("Read Symbols", false),
                Stat.Value.Multiple.Option.Selectable("Light", false),
                Stat.Value.Multiple.Option.Selectable("Fire", false),
                Stat.Value.Multiple.Option.Selectable("Jump", false),
                Stat.Value.Multiple.Option.Selectable("Detect Trap", false),
                Stat.Value.Multiple.Option.Selectable("Create Water", false)
            )
        )
        assertThat(
            adventureStats
                .map { it.toTyped() }
                .filter { it.typedStat.type == Stat.Type.MULTI_OPTION }
                .count {
                    it.typedStat.currentValue == expectedStatValue &&
                            it.typedStat.initialValue == expectedStatValue
                },
            `is`(1)
        )
    }

    @Test
    fun multipleOptionRepeatableStatTest() {

        // See caveats of firstMatching
        fun matchFirstRepeatableStatOptionViewComponent(id: Int): ViewInteraction =
            onView(
                firstMatching(
                    allOf(
                        withId(id),
                        isDescendantOfA(withId(R.id.view_stat_multi_option_values_recycler))
                    )
                )
            )

        activityWithMultipleOptionRepeatableStats()

        // Check for controls of MultipleOptionStatIntView
        onView(withId(R.id.view_stat_multi_option_name)).check(matches(isDisplayed()))
        onView(withId(R.id.view_stat_multi_option_values_recycler)).check(matches(isDisplayed()))

        // Select first option of MultipleOptionStatIntView
        // Click subtract button and check stat value
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_subtract_btn)
            .perform(click())
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_value)
            .check(matches(withText("0")))

        // Click add button and check stat value
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_add_btn).perform(click())
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_value)
            .check(matches(withText("1")))
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_add_btn).perform(click())
        matchFirstRepeatableStatOptionViewComponent(R.id.view_stat_int_value)
            .check(matches(withText("2")))

        // Create Adventure
        onView(withText(R.string.activity_new_character_create)).perform(click())

        // Assert activity finished with expected result
        assertThat(newCharacterActivityRule.activityResult.resultCode, `is`(Activity.RESULT_OK))

        // Assert that the adventure was created correctly
        val adventures = dbRule.db.adventureDao().listAll().observeAndAwait()
        assertThat(adventures.size, `is`(1))

        val adventureStats = runBlocking {
            dbRule.db.statDao().listForAdventure(adventures[0].adventure.id)
        }
        val expectedStatValue = Stat.Value.Multiple(
            listOf(
                Stat.Value.Multiple.Option.Repeatable("Electric Lash", 2),
                Stat.Value.Multiple.Option.Repeatable("Assault Blaster", 0),
                Stat.Value.Multiple.Option.Repeatable("Grenade", 0),
                Stat.Value.Multiple.Option.Repeatable("Gravity Bomb", 0)
            )
        )
        assertThat(
            adventureStats
                .map { it.toTyped() }
                .filter { it.typedStat.type == Stat.Type.MULTI_OPTION_REPEAT }
                .count {
                    it.typedStat.currentValue == expectedStatValue &&
                            it.typedStat.initialValue == expectedStatValue
                },
            `is`(1)
        )
    }
}
