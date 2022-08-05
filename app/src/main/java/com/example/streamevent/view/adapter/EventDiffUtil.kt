package com.example.streamevent.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.streamevent.model.dto.Event

class EventDiffUtil(private val oldEvents: List<Event>, private val newEvents: List<Event>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldEvents.size

    override fun getNewListSize(): Int = newEvents.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldEvents[oldItemPosition].id == newEvents[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldEvents[oldItemPosition] == newEvents[newItemPosition]
}