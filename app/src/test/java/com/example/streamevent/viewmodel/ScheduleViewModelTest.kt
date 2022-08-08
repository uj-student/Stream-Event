package com.example.streamevent.viewmodel

import com.example.streamevent.BaseMockResponses.eventId
import com.example.streamevent.BaseMockResponses.eventSubtitle
import com.example.streamevent.BaseMockResponses.eventThumbnailUrl
import com.example.streamevent.BaseMockResponses.scheduleResponse
import com.example.streamevent.BaseUnitTest
import com.example.streamevent.getOrAwaitValue
import com.example.streamevent.model.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ScheduleViewModelTest : BaseUnitTest() {
    @Mock
    lateinit var repository: Repository

    @InjectMocks
    lateinit var scheduleViewModel: ScheduleViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        scheduleViewModel = ScheduleViewModel(repository)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun shouldCallScheduleRepository() = runTest {
        `when`(repository.getSchedule()).thenReturn(emptyList())

        scheduleViewModel.getSchedule()

        verify(repository, times(1)).getSchedule()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun getSchedule() = runTest {
        `when`(repository.getSchedule()).thenReturn(scheduleResponse)

        scheduleViewModel.getSchedule()

        with(scheduleViewModel.scheduleLiveData.getOrAwaitValue()) {
            assertNotNull(this)
            assertTrue(this?.isNotEmpty() == true)
            assertEquals(eventId, this?.last()?.id)
            assertEquals(eventThumbnailUrl, this?.last()?.imageUrl)
            assertEquals(eventSubtitle, this?.last()?.subtitle)
        }
    }
}