package com.samsul.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.samsul.moviecatalogue.ui.home.HomeActivity
import com.samsul.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.StringBuilder

class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource())
    }

    @Test
    fun loadListMovie() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))

    }

    @Test
    fun loadListTvShow() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(19))
    }

    @Test
    fun movieBehaviour() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).apply {
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            pressBack()
            check(matches(isDisplayed()))
            perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(19, click()))
            pressBack()
        }
    }

    @Test
    fun detailMovie() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))

        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))

        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))
    }

    @Test
    fun detailTvShow() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))

        onView(withId(R.id.tvGenre)).check(matches(isDisplayed()))

        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))
    }
}