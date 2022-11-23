package com.ssafy.guseul.presentation

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants.ALREADY_USER_EXISTS
import com.ssafy.guseul.databinding.ActivityLoginBinding
import com.ssafy.guseul.databinding.ActivityMainBinding
import com.ssafy.guseul.presentation.onboarding.OnBoardingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_login_host) as NavHostFragment

        navController = navHostFragment.findNavController()
        alreadyExistsUserInfo = intent.getBooleanExtra(ALREADY_USER_EXISTS,false)
    }

    override fun onBackPressed() {
        val f = getCurrentFragment()
        if (f is OnBoardingFragment) {
            finishAffinity()
            return
        }

        if (!navController.navigateUp()) {
            super.onBackPressed()
            navController.popBackStack()
        }
    }

    //현재 프래그먼트 찾기
    private fun getCurrentFragment(): Fragment? {
        val currentFragmentContainer = supportFragmentManager.findFragmentById(R.id.nav_login_host)
        val currentFragmentClassName =
            (navController.currentDestination as FragmentNavigator.Destination).className

        return currentFragmentContainer?.childFragmentManager?.fragments?.filterNotNull()?.find {
            it.javaClass.name == currentFragmentClassName
        }
    }
    companion object{
        var alreadyExistsUserInfo = false
    }
}