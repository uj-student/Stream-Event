package com.example.streamevent.view

import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.example.streamevent.R
import com.example.streamevent.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
//not working
class EventFragmentTest {
    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun shouldTestEventFragment() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<EventFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.progressIndicator)).check(matches(isDisplayed()))

        Thread.sleep(5000)

        onView(withId(R.id.eventsMenuItem)).check(matches((isClickable())))
        onView(withId(R.id.eventsMenuItem)).check(matches((isDisplayed())))

        onView(withId(R.id.scheduleMenuItem)).check(matches((isClickable())))
        onView(withId(R.id.scheduleMenuItem)).check(matches((isDisplayed())))

        onView(withId(R.id.scheduleMenuItem)).perform(click())

        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(R.id.scheduleMenuItem), withContentDescription("Schedule"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottomNavigationView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
//        bottomNavigationItemView.perform(click())
        onView(withId(R.id.scheduleMenuItem)).perform(click())

        Thread.sleep(5000)

        verify(navController).navigate(
            ScheduleFragmentDirections.actionScheduleFragmentToEventFragment()
        )
    }

    private fun childAtPosition(parentMatcher: Matcher<View>, position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) && view == parent.getChildAt(position)
            }
        }
    }
}