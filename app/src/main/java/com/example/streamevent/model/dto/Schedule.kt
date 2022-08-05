package com.example.streamevent.model.dto

class Schedule : BaseResponse()

//Schedule and event will be treated as the same, difference being event has video url
//The 2 objects are kept in code to signify / acknowledge that their structure may change in future
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