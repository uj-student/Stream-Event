package com.example.streamevent

import com.example.streamevent.model.dto.Event

object BaseMockResponses {

    const val eventId = "8"
    const val eventTitle = "Dodgers @ Cardinals"
    const val eventSubtitle = "MLB Baseball"
    const val eventDate = "2022-08-06T09:30:42.719Z"
    const val eventThumbnailUrl =
        "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310798917361_image-header_pDach_1554667420000.jpeg?alt=media&token=7c15b201-0842-4aaf-acb4-6bd2c6a5bb4d"
    const val eventVideoUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"

    val eventsResponse = listOf<Event>(
        Event().apply {
            id = eventId
            title = eventTitle
            subtitle = eventSubtitle
            date = eventDate
            imageUrl = eventThumbnailUrl
            videoUrl = eventVideoUrl
        },
        Event(),
        Event()
    )


}