package com.example.streamevent.viewmodel

import com.example.streamevent.BaseMockResponses.eventDate
import com.example.streamevent.BaseMockResponses.eventId
import com.example.streamevent.BaseMockResponses.eventVideoUrl
import com.example.streamevent.BaseMockResponses.eventsResponse
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
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest : BaseUnitTest() {
    @Mock
    private lateinit var repository: Repository

    @InjectMocks
    lateinit var eventViewModel: EventsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        eventViewModel = EventsViewModel(repository)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    @DisplayName("Should verify that repository is called")
    fun shouldCallEventsRepository() = runTest {
        `when`(repository.getEvents()).thenReturn(emptyList())

        eventViewModel.getEvents()

        verify(repository, times(1)).getEvents()
        verifyNoMoreInteractions(repository)
    }

    @Test
    @DisplayName("Should assert that correct response is received")
    fun shouldReturnEventsResponse() = runTest {
        `when`(repository.getEvents()).thenReturn(eventsResponse)

        eventViewModel.getEvents()

        with(eventViewModel.eventsLiveData.getOrAwaitValue()) {
            assertNotNull(this)
            assertTrue(this?.isNotEmpty() == true)
            assertEquals(eventId, this?.last()?.id)
            assertEquals(eventDate, this?.last()?.date)
            assertEquals(eventVideoUrl, this?.last()?.videoUrl)
        }
    }
}