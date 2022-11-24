package com.ssafy.guseul.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ssafy.guseul.ApplicationClass
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants.ALREADY_USER_EXISTS
import com.ssafy.guseul.common.util.Constants.NO_USER_ID
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.common.view.LoadingDialog
import com.ssafy.guseul.databinding.ActivitySplashBinding
import com.ssafy.guseul.presentation.LoginActivity
import com.ssafy.guseul.presentation.MainActivity
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.AndroidEntryPoint


/* 스플래시에서 토큰 유효성 검사 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        checkTokenValidation()
    }

    private fun checkTokenValidation() {
        // 무조건 2초 후 통신 or 토큰이 있으면 검사할지
        Handler(Looper.getMainLooper()).postDelayed({
            if (ApplicationClass.preferences.accessToken == null) {
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                startObserveSplashViewModel()
                splashViewModel.getUserInfo()
            }
        }, 1000)
    }

    private fun startObserveSplashViewModel() {
        splashViewModel.userInfo.observe(this) { response ->
            when (response) {
                is ViewState.Loading -> {
                    setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    setLoadingDialog(false)
                    finish()
                    if(response.value?.userId == NO_USER_ID){
                        startActivity(Intent(this, LoginActivity::class.java).apply {
                            putExtra(ALREADY_USER_EXISTS, true)
                        })
                    }else{
                        startActivity(Intent(this, MainActivity::class.java))
                        ApplicationClass.userId = response.value?.userId!!
                    }
                }
                is ViewState.Error -> {
                    setLoadingDialog(false)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(LoadingDialog.getLoadingDialogInstance(this)?.isShowing == true){
            LoadingDialog.getLoadingDialogInstance(this)?.dismiss()
        }
    }
}