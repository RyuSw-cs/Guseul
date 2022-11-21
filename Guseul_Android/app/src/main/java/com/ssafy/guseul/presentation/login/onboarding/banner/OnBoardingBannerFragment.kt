package com.ssafy.guseul.presentation.login.onboarding.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants
import com.ssafy.guseul.common.util.Constants.BUNDEL_KEY_ON_BOARDING_BANNER_DATA
import com.ssafy.guseul.databinding.FragmentOnboardingBannerBinding
import com.ssafy.guseul.presentation.login.onboarding.model.OnBoardingBannerModel


class OnBoardingBannerFragment : Fragment() {

    private lateinit var bannerData: OnBoardingBannerModel
    private lateinit var binding: FragmentOnboardingBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_onboarding_banner,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        bannerData = arguments?.getParcelable(BUNDEL_KEY_ON_BOARDING_BANNER_DATA)!!
        with(bannerData) {
            binding.ivLoginBanner.setImageResource(img)
            binding.tvBannerContent.text = message
        }
    }
}