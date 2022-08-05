package com.example.streamevent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamevent.databinding.FragmentEventBinding
import com.example.streamevent.view.adapter.EventAdapter
import com.example.streamevent.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EventFragment : Fragment() {
    private lateinit var binding: FragmentEventBinding
    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        setUpEventObserver()
    }

    private fun setUpUI() {
        binding.eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = EventAdapter()
        }
    }

    private fun setUpEventObserver() {
        eventsViewModel.eventsLiveData.observe(viewLifecycleOwner) {
            (binding.eventRecyclerView.adapter as EventAdapter).setEventList(it)
        }
    }

}