package com.example.streamevent.model.repo

import com.example.streamevent.model.dto.Event
import com.example.streamevent.model.dto.Schedule
import javax.inject.Inject

class Repository @Inject constructor(private val remoteAPI: RemoteAPI) {
    suspend fun getEvents(): List<Event> = remoteAPI.getEvents()

    suspend fun getSchedule(): List<Schedule> = remoteAPI.getSchedule()
}