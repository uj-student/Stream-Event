package com.example.streamevent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamevent.model.dto.Event
import com.example.streamevent.model.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: Repository): ViewModel() {
    val eventsLiveData = MutableLiveData<List<Event>>()
    val showProgressIndicator = MutableLiveData<Boolean>(true)

    init {
        viewModelScope.launch {
            eventsLiveData.postValue(repository.getEvents().sortedBy { it.date })
            showProgressIndicator.postValue(false)
        }
    }
}