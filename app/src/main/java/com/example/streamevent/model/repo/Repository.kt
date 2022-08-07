package com.example.streamevent.model.repo

import android.util.Log
import com.example.streamevent.model.dto.Event
import com.example.streamevent.model.dto.Schedule
import javax.inject.Inject

open class Repository @Inject constructor(private val remoteAPI: RemoteAPI) {
    suspend fun getEvents(): List<Event> = try {
        remoteAPI.getEvents()
    } catch (e: Exception) {
        Log.e("Events repo -> ", e.stackTraceToString())
        listOf()
    }

    suspend fun getSchedule(): List<Schedule> = try {
        remoteAPI.getSchedule()
    } catch (e: Exception) {
        Log.e("Schedule repo -> ", e.stackTraceToString())
        listOf()
    }
}