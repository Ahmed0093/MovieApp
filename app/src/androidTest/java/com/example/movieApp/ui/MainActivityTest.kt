package com.example.movieApp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.myapplication.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {
    @Rule
    @JvmField
    var activityActivityTestRule: ActivityTestRule<MainActivity> =
        object : ActivityTestRule<MainActivity>(MainActivity::class.java) {

        }


    @Test
    @Throws(Exception::class)
    fun journeyFLow() {
        Thread.sleep(3000)
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        Thread.sleep(1000)

    }

    @Test
    @Throws(Exception::class)
    fun journeyFLowWithSearchFirst() {
        Thread.sleep(3000)
        onView(withId(R.id.et_search)).perform(typeText("day"), pressImeActionButton())
        Thread.sleep(1000)
        onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        Thread.sleep(1000)
    }


}