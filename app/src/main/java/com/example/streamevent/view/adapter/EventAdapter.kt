package com.example.streamevent.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.streamevent.databinding.EventItemBinding
import com.example.streamevent.model.dto.Event
import com.example.streamevent.util.formatDate
import com.example.streamevent.util.loadImage
import com.example.streamevent.view.EventFragmentDirections
import javax.inject.Inject

class EventAdapter @Inject constructor() : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    var eventDisplayItems: List<Event> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        holder.bind(eventDisplayItems[position])
        holder.itemView.setOnClickListener {
            if(eventDisplayItems[position].videoUrl.isNotEmpty()) {
                println("hello")
                it.findNavController().navigate(EventFragmentDirections.actionEventFragmentToPlaybackFragment())
            }
        }
    }

    override fun getItemCount() = eventDisplayItems.size

    inner class ViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(eventItem: Event) {
            with(binding) {
                eventTitle.text = eventItem.title
                eventSubTitle.text = eventItem.subtitle
                eventDateTime.text = eventItem.date.formatDate()
                eventThumbnail.loadImage(eventItem.imageUrl)
            }
        }
    }
}