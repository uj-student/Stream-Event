package com.example.streamevent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.streamevent.databinding.ActivityMainBinding
import com.example.streamevent.viewmodel.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val eventsViewModel: EventsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.eventsMenuItem -> {
                    Toast.makeText(baseContext, "Events!!", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.scheduleMenuItem -> {
                    Toast.makeText(baseContext, "Schedule!!", Toast.LENGTH_LONG).show()
                    true
                }
                else -> {
                    false
                }
            }
        }

//        eventsViewModel.eventsLiveData.observe(this) { events ->
//            events.forEach {
//                println("hello")
//                println(it.title)
//            }
//        }

    }
}