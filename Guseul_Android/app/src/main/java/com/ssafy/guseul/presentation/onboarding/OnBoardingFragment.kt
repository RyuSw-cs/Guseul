package com.ssafy.guseul.presentation.onboarding

import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentOnboardingBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnboardingBinding>(R.layout.fragment_onboarding) {

    private val viewModel by viewModels<OnBoardingViewModel>()

    override fun initView() {
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { response ->
//            when(response){
//                is ViewState.Loading -> {
//
//                }
//                is ViewState.Success -> {
//
//                }
//                is ViewState.Error -> {
//
//                }
        }
    }
}
