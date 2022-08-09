package com.example.streamevent.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.streamevent.R
import com.example.streamevent.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//this is open for the ui tests
open class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        setUpNavigationBehaviour(navHostFragment.navController)
    }

    private fun setUpNavigationBehaviour(navController: NavController) {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when {
                (item.itemId == R.id.eventsMenuItem) && (navController.currentDestination?.id != R.id.eventFragment) -> {
                    Toast.makeText(baseContext, getString(R.string.events_message), Toast.LENGTH_LONG).show()
                    navController.navigate(ScheduleFragmentDirections.actionScheduleFragmentToEventFragment())
                    true
                }
                (item.itemId == R.id.scheduleMenuItem) && (navController.currentDestination?.id != R.id.scheduleFragment) -> {
                    Toast.makeText(baseContext, getString(R.string.schedule_message), Toast.LENGTH_LONG).show()
                    navController.navigate(EventFragmentDirections.actionEventFragmentToScheduleFragment())
                    true
                }
                else -> false //Do nothing => Don't attempt navigation - already on screen
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.eventFragment -> toggleBottomNavigationBarVisibility(true)
                R.id.scheduleFragment -> toggleBottomNavigationBarVisibility(true)
                else -> toggleBottomNavigationBarVisibility(false)
            }
        }
    }

    private fun toggleBottomNavigationBarVisibility(show: Boolean) {
        binding.bottomNavigationView.isVisible = show
    }

    fun toggleProgressIndicatorVisibility(visibility: Int) {
        binding.progressIndicator.visibility = visibility
    }

    fun toggleErrorMessageVisibility(visibility: Int) {
        binding.errorMessage.visibility = visibility
    }
}