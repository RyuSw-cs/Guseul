package com.ssafy.guseul.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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

        navController = navHostFragment.navController

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