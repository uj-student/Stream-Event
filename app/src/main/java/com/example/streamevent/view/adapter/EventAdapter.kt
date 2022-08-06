package com.example.streamevent.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.streamevent.databinding.EventItemBinding
import com.example.streamevent.model.dto.Event
import com.example.streamevent.util.formatDate
import com.example.streamevent.util.loadImage
import com.example.streamevent.view.EventFragmentDirections
import javax.inject.Inject

class EventAdapter @Inject constructor() : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    private var eventDisplayItems: List<Event> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(EventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) = with(eventDisplayItems[position]) {
        holder.bind(this)
        holder.itemView.setOnClickListener {
            if (this.videoUrl.isNotEmpty()) {
                it.findNavController().navigate(EventFragmentDirections.actionEventFragmentToPlaybackFragment(this))
            }
        }
    }

    override fun getItemCount(): Int = eventDisplayItems.size

    fun setEventList(eventList: List<Event>) {
        val diffUtil = EventDiffUtil(this.eventDisplayItems, eventList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.eventDisplayItems = eventList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(eventItem: Event) = with(binding) {
            eventTitle.text = eventItem.title
            eventSubTitle.text = eventItem.subtitle
            eventDateTime.text = eventItem.date.formatDate()
            eventThumbnail.loadImage(eventItem.imageUrl)
        }
    }
}