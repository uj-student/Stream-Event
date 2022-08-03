package com.example.streamevent

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.streamevent.databinding.ActivityMainBinding
import com.example.streamevent.view.EventFragmentDirections
import com.example.streamevent.view.ScheduleFragmentDirections
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

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        setUpNavigation(navHostFragment.navController)

    }

    private fun setUpNavigation(navController: NavController) {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when {
                (item.itemId == R.id.eventsMenuItem) && (navController.currentDestination?.id != R.id.eventFragment) -> {
                    Toast.makeText(baseContext, "Events!!", Toast.LENGTH_LONG).show()
                    navController.navigate(ScheduleFragmentDirections.actionScheduleFragmentToEventFragment())
                    true
                }
                (item.itemId == R.id.scheduleMenuItem) && (navController.currentDestination?.id != R.id
                    .scheduleFragment) -> {
                    Toast.makeText(baseContext, "Schedule!!", Toast.LENGTH_LONG).show()
                    navController.navigate(EventFragmentDirections.actionEventFragmentToScheduleFragment())
                    true
                }
                else -> false //Do nothing => Don't attempt navigation - already on screen
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