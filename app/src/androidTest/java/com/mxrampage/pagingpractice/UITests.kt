package com.mxrampage.pagingpractice

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mxrampage.pagingpractice.detail.DetailFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITests {

    private lateinit var query: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        query = "bird"
    }

    @Test
    fun doBasicSearch() {
        onView(withId(R.id.searchRequest))
            .perform(typeText(query), pressImeActionButton())

        onView(withId(R.id.recyclerSearch))
            .check(RecyclerViewItemCountAssertion(10))
    }

    @Test
    fun loadDetails() {
        Intents.init()
        onView(withId(R.id.searchRequest))
            .perform(typeText(query), pressImeActionButton())

        onView(withId(R.id.recyclerSearch))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        intended(hasComponent(DetailFragment::class.simpleName))
        Intents.release()
    }
}

class RecyclerViewItemCountAssertion(var expectedCount: Int) : ViewAssertion {

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) throw noViewFoundException
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assert(adapter?.itemCount == expectedCount)
    }
}
