package com.example.streamevent.model.repo

import com.example.streamevent.model.data.Event
import com.example.streamevent.model.data.Schedule
import retrofit2.http.GET

interface RemoteAPI {

    @GET("/getEvents")
    suspend fun getEvents(): List<Event>

    @GET("/getSchedule")
    suspend fun getSchedule(): List<Schedule>
}