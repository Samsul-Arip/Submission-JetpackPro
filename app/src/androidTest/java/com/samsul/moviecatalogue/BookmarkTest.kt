package com.samsul.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.samsul.moviecatalogue.ui.home.HomeActivity
import com.samsul.moviecatalogue.ui.home.HomeBookmarkActivity
import com.samsul.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookmarkTest {

    @Rule
    @JvmField
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Rule
    @JvmField
    var scenarioRule = ActivityScenarioRule(HomeBookmarkActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }

    @Test
    fun getBookmarkMovie() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        ))
        onView(allOf(withId(R.id.menu_favorite))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.menu_favorite))).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(allOf(withId(R.id.rvTvShow))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rvTvShow)))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        ))
        onView(allOf(withId(R.id.menu_favorite))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.menu_favorite))).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.img_collection)).perform(click())

        onView(allOf(withId(R.id.tabs_bookmark))).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.rvBookmarkMovie))).check(matches(isDisplayed()))

        onView(withId(R.id.rvBookmarkMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        ))

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_favorite)).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.view_pager2Bookmark)).perform(swipeLeft())
        onView(withId(R.id.rv_bookmark_tvshow)).check(matches(isDisplayed()))

        onView(withId(R.id.rv_bookmark_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_favorite)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_favorite)).perform(click())

        onView(isRoot()).perform(ViewActions.pressBack())

    }
}