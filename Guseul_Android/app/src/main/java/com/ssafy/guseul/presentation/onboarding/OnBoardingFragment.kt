package com.ssafy.guseul.presentation.onboarding

import com.ssafy.guseul.presentation.uimodel.onboarding.OnBoardingBannerModel
import android.util.Log
import androidx.fragment.app.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.databinding.FragmentOnboardingBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import com.ssafy.guseul.presentation.onboarding.adapter.OnBoardingBannerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()
    private val kakaoLoginAccessCallback = initKakaoLoginCallback()

    override fun initView() {
        binding.apply {
            btnKakaoLogin.setOnClickListener {
                kakaoLogin()
            }

            vpBanner.adapter = OnBoardingBannerAdapter(this@OnBoardingFragment)
            ciBanner.setViewPager(binding.vpBanner)
        }

        startObserveOnBoardingViewModel()
    }

    private fun initKakaoLoginCallback(): (OAuthToken?, Throwable?) -> Unit =
        { oAuthToken, throwable ->
            if (throwable != null) {
                Log.e(TAG, "initKakaoLoginCallback: ${throwable.message}")
            } else {
                Log.d(TAG, "initKakaoLoginCallback: login success")
                UserApiClient.instance.me { _, _ ->
                    onBoardingViewModel.getJWTWithKakao(oAuthToken?.accessToken!!)
                }
            }
        }

    private fun kakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(
                requireContext(),
                callback = kakaoLoginAccessCallback
            )
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                requireContext(),
                callback = kakaoLoginAccessCallback
            )
        }
    }

    private fun startObserveOnBoardingViewModel() {
        onBoardingViewModel.accessToken.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    requireContext().setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    requireContext().setLoadingDialog(false)
                    val result = response.value
                    if (result?.accessToken?.isEmpty() == false) {
                        navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToJoinFragment())
                    }
                }
                is ViewState.Error -> {
                    requireContext().setLoadingDialog(false)
                }
            }
        }
    }

    companion object {
        private const val TAG = "OnBoardingFragment"
    }
}
