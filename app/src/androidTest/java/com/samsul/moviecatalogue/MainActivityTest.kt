package com.samsul.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.samsul.moviecatalogue.ui.fragment.bookmark.TvShowsBookmarkFragment
import com.samsul.moviecatalogue.ui.fragment.movie.MovieFragment
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowFragment
import com.samsul.moviecatalogue.ui.home.HomeActivity
import com.samsul.moviecatalogue.ui.home.HomeBookmarkActivity
import com.samsul.moviecatalogue.ui.home.SectionPagerAdapter
import com.samsul.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

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
    fun detailMovie() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))

        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))

        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))

        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))

        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))

        onView(withId(R.id.imgBackDetail)).perform(click())

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

        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))

        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))

        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))

        onView(withId(R.id.imgBackDetail)).perform(click())
    }

    @Test
    fun loadBookmarkMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.img_collection)).perform(click())
        onView(withId(R.id.rvBookmarkMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvBookmarkMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTime)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvStatus)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSinopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.imagePreview)).check(matches(isDisplayed()))

        onView(withId(R.id.menu_favorite)).perform(click())

    }

    @Test
    fun getBookmarkTvShows() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager2)).perform(swipeLeft())
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())
    }
}