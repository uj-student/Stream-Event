package com.example.streamevent.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.streamevent.R
import com.example.streamevent.databinding.ActivityMainBinding
import com.example.streamevent.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val baseViewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeProgressIndicator()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        setUpNavigationBehaviour(navHostFragment.navController)
    }

    private fun setUpNavigationBehaviour(navController: NavController)  {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when {
                (item.itemId == R.id.eventsMenuItem) && (navController.currentDestination?.id != R.id.eventFragment) -> {
                    Toast.makeText(baseContext, "Events!!", Toast.LENGTH_LONG).show()
                    navController.navigate(ScheduleFragmentDirections.actionScheduleFragmentToEventFragment())
                    true
                }
                (item.itemId == R.id.scheduleMenuItem) && (navController.currentDestination?.id != R.id.scheduleFragment) -> {
                    Toast.makeText(baseContext, "Schedule!!", Toast.LENGTH_LONG).show()
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

    private fun observeProgressIndicator() {
        baseViewModel.showProgressIndicator = MutableLiveData()
        baseViewModel.showProgressIndicator.observeForever {
            if (it) {
                binding.progressIndicator.visibility = View.VISIBLE
                println("\nobserveProgressIndicator -> shown\n\n")
            } else {
                binding.progressIndicator.visibility = View.GONE
                println("\nobserveProgressIndicator -> hidden\n\n")
            }
        }
    }

    private fun toggleBottomNavigationBarVisibility(show: Boolean) {
        binding.bottomNavigationView.isVisible = show
    }
}