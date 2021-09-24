package com.corbellini.githubapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.corbellini.presentation.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExampleUnitTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)


    @Test
    fun shouldDisplayDataOnSearch() {
        Espresso.onView(ViewMatchers.withId(com.corbellini.presentation.R.id.list_repo_recycler_view)).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))
        ///Espresso.onView((ViewMatchers.withId(R.id.search_results_recycler_view)).check(matches(isDisplayed()))
    }
}