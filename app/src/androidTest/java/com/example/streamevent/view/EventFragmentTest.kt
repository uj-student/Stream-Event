package com.example.streamevent.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.streamevent.R
import com.example.streamevent.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
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

//        doNothing().`when`(EventFragment().baseActivity)
//        `when`(EventFragment().baseActivity).thenReturn(this)

        launchFragmentInHiltContainer<EventFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

//        onView(withId(R.id.scheduleMenuItem)).check(matches(isClickable()))
        onView(withId(R.id.scheduleMenuItem)).perform(click())

        verify(navController).navigate(
            EventFragmentDirections.actionEventFragmentToScheduleFragment()
        )
    }
}