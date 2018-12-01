/*
 * MyBehaviorTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 11/1/18 1:00 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tampan.kucingapes.submission5final.model.LeagueWithId


@RunWith(AndroidJUnit4::class)
class MyBehaviorTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBehavior() {

        onView(allOf(withId(R.id.spinner), isDisplayed())).perform(click())
        onData(allOf(`is`(instanceOf(LeagueWithId::class.java)))).atPosition(3).perform(click())
        Thread.sleep(3000)

        onView(allOf(withId(R.id.list_match), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(allOf(withId(R.id.list_match), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(2000)

        onView(withId(R.id.favorite_action)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        Thread.sleep(2000)

        onView(withId(R.id.teams)).perform(click())
        Thread.sleep(2000)

        onView(allOf(withId(R.id.list_team), isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(allOf(withId(R.id.list_team), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(2000)
        onView(withId(R.id.favorite_action)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.action_search)).perform(click())
        Thread.sleep(2000)

        onView(withId(android.support.v7.appcompat.R.id.search_src_text))
            .perform(typeText("liverpool"))
        Thread.sleep(2000)
        onView(withId(R.id.list_search))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)

        onView(allOf(withText("PLAYER"), isDescendantOfA(withId(R.id.tabs))))
            .perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.list_player))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
        onView(withId(R.id.favorites)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.view_pager)).perform(swipeDown())
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.view_pager)).perform(swipeDown())
        Thread.sleep(2000)

    }
}