package com.example.streamevent.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.streamevent.model.dto.Schedule
import com.example.streamevent.model.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val scheduleLiveData = MutableLiveData<List<Schedule>>()

    fun getSchedule() = viewModelScope.launch { scheduleLiveData.postValue(repository.getSchedule().sortedBy { it.date }) }
}