package com.example.streamevent.view

import android.content.Context
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
    private lateinit var baseActivity: MainActivity
    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentEventBinding.inflate(inflater, container, false)
        baseActivity.toggleProgressIndicatorVisibility(View.VISIBLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        getEventsAndObserve()
    }

    private fun setUpUI() {
        binding.eventRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = EventAdapter()
        }
    }

    private fun getEventsAndObserve() {
        eventsViewModel.getEvents()
        eventsViewModel.eventsLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                baseActivity.toggleErrorMessageVisibility(View.VISIBLE)
            } else {
                (binding.eventRecyclerView.adapter as EventAdapter).setEventList(it)
                baseActivity.toggleErrorMessageVisibility(View.GONE)
            }
            baseActivity.toggleProgressIndicatorVisibility(View.GONE)
        }
    }
}