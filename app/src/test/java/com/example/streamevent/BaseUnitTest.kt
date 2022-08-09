package com.example.streamevent

import MainCoroutineScopeRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
open class BaseUnitTest {
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}

fun <T> LiveData<T>.attachDisposableObserver() {
    this.observeForever(object : Observer<T> {
        override fun onChanged(t: T) {
            this@attachDisposableObserver.removeObserver(this)
        }
    })
}

fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    this.observeForever(object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    })
    if (!latch.await(time, timeUnit)) {
        throw  TimeoutException("liveData not set")
    }

    return data
}