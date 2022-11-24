package com.ssafy.guseul.presentation.onboarding

import android.content.Intent
import com.ssafy.guseul.presentation.uimodel.onboarding.OnBoardingBannerModel
import android.util.Log
import androidx.fragment.app.viewModels
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.ssafy.guseul.ApplicationClass
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.databinding.FragmentOnboardingBinding
import com.ssafy.guseul.presentation.LoginActivity
import com.ssafy.guseul.presentation.MainActivity
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

    // 스플래시 -> 토큰

    private fun startObserveOnBoardingViewModel() {
        onBoardingViewModel.accessToken.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    requireActivity().setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    requireActivity().setLoadingDialog(false)
                    val result = response.value
                    //기존 토큰이 있다면 추가사항 입력을 하지 않음
                    if (result?.accessToken?.isEmpty() == false) {
                        if (LoginActivity.alreadyExistsUserInfo) {
                            activity?.finish()
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                        } else {
                            navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToJoinFragment())
                        }
                    }
                }
                is ViewState.Error -> {
                    requireActivity().setLoadingDialog(false)
                }
            }
        }
    }

    companion object {
        private const val TAG = "OnBoardingFragment"
    }
}
