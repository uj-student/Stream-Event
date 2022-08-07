package com.example.streamevent.viewmodel

import com.example.streamevent.BaseMockResponses.eventsResponse
import com.example.streamevent.model.repo.Repository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EventsViewModelTest {
    @Mock
    lateinit var repository: Repository

    lateinit var eventViewModel: EventsViewModel


    @Before
    fun setUp() {
        eventViewModel = EventsViewModel(repository)
    }

    @Test
//    @DisplayName("")
    fun shouldCallEventsLiveData() {
        `when`(eventViewModel.eventsLiveData.value).thenReturn((eventsResponse))
        eventViewModel.getEvents()

        verify(eventViewModel).getEvents()
        verifyNoMoreInteractions(eventViewModel)
    }
}