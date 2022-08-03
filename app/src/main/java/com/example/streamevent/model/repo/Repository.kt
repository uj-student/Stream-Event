package com.example.streamevent.model.repo

import javax.inject.Inject

class Repository @Inject constructor(private val remoteAPI: RemoteAPI) {
    suspend fun getEvents() = remoteAPI.getEvents()
}