package com.example.streamevent.view

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
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
    private lateinit var countDownTimer: CountDownTimer

    companion object {
        private const val REFRESH_COUNTDOWN_INTERVAL = 30000L
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = requireActivity() as MainActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        baseActivity.toggleProgressIndicatorVisibility(View.VISIBLE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
        getScheduleAndObserve()
    }

    override fun onResume() {
        super.onResume()
        countDownTimer = getCountDownTimer()
        countDownTimer.start()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
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

    private fun getScheduleAndObserve() {
        scheduleViewModel.getSchedule()
        scheduleViewModel.scheduleLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                with(binding.scheduleRecyclerView) {
                    itemAnimator = null
                    (adapter as EventAdapter).setEventList(toEvent(it))
                }
                baseActivity.toggleErrorMessageVisibility(View.GONE)
            }
            baseActivity.toggleProgressIndicatorVisibility(View.GONE)
        }
    }

    // this assumption is the list should be refreshed when user on the screen
    private fun getCountDownTimer(): CountDownTimer {
        return if (::countDownTimer.isInitialized) {
            countDownTimer
        } else {
            object : CountDownTimer(REFRESH_COUNTDOWN_INTERVAL, REFRESH_COUNTDOWN_INTERVAL) {
                override fun onTick(millisUntilFinished: Long) {}

                override fun onFinish() {
                    getScheduleAndObserve()
                    countDownTimer.start()
                }
            }
        }
    }
}