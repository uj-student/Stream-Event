package com.example.streamevent.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streamevent.databinding.EventItemBinding
import com.example.streamevent.model.dto.Schedule
import com.example.streamevent.util.loadImage
import javax.inject.Inject

class EventAdapter @Inject constructor() : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    var displayItems: List<Schedule> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        holder.bind(displayItems[position])
    }

    override fun getItemCount() = displayItems.size

    inner class ViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eventItem: Schedule) {
            with(binding) {
                eventTitle.text = eventItem.title
                eventSubTitle.text = eventItem.subtitle
                eventDateTime.text = eventItem.date
                eventThumbnail.loadImage(eventItem.imageUrl)
            }
        }
    }
}