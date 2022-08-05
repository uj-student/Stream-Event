package com.example.streamevent.model.dto

class Schedule : BaseResponse()

fun toEvent(schedules: List<Schedule>): List<Event> {
    val events: MutableList<Event> = mutableListOf()
    schedules.forEach {
        events.add(Event().apply {
            this.date = it.date
            this.id = it.id
            this.imageUrl = it.imageUrl
            this.subtitle = it.subtitle
            this.title = it.title
        })
    }
    return events
}