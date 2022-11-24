package com.ssafy.guseul.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.navigation_main)

        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.itemIconTintList = null

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigation.visibility = when (destination.id) {
                R.id.BoardFragment -> View.VISIBLE
                R.id.PlaceFragment -> View.VISIBLE
                R.id.MyPageFragment -> View.VISIBLE
                else -> View.GONE
            }
        }

        //처음 프래그먼트 보이는 방법
//        val startDestination = when {
//            isGranted().not() -> {
//                R.id.permissionFragment
//            }
//            TiccoApplication.preferences.accessToken == null -> {
//                R.id.onboardingFragment
//            }
//            else -> R.id.homeFragment
//        }
//
//        navGraph.setStartDestination(startDestination)

        navController.graph = navGraph
    }
}