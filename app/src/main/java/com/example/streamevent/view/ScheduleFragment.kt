package com.example.streamevent.view

import android.content.Context
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
import com.example.streamevent.model.dto.toEvent
import com.example.streamevent.view.adapter.EventAdapter
import com.example.streamevent.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private lateinit var binding: FragmentScheduleBinding
    private lateinit var baseActivity: MainActivity
    private val scheduleViewModel: ScheduleViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        baseActivity.showProgressIndicator()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        getScheduleAndObserver()

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

    private fun getScheduleAndObserver() {
        scheduleViewModel.getSchedule()
        scheduleViewModel.scheduleLiveData.observe(viewLifecycleOwner) {
            (binding.scheduleRecyclerView.adapter as EventAdapter).setEventList(toEvent(it))
            baseActivity.hideProgressIndicator()
        }
    }
}