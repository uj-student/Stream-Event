package com.example.streamevent.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamevent.databinding.FragmentScheduleBinding
import com.example.streamevent.view.adapter.EventAdapter
import com.example.streamevent.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private lateinit var binding: FragmentScheduleBinding
    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        setUpUI()
        setUpScheduleObserver()
        return binding.root
    }

    //ToDo: look at duplicate code with EventFragment
    private fun setUpUI() {
        binding.scheduleRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = EventAdapter()
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.VERTICAL))
        }
    }

    private fun setUpScheduleObserver() {
        scheduleViewModel.scheduleLiveData.observe(viewLifecycleOwner) { schedules ->
            with(binding.scheduleRecyclerView.adapter as EventAdapter) {
                this.displayItems = schedules.sortedBy { it.date }
                notifyDataSetChanged()
            }
        }
    }
}