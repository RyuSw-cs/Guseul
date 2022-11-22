package com.ssafy.guseul.presentation.onboarding.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants.BUNDEL_KEY_ON_BOARDING_BANNER_POSITION
import com.ssafy.guseul.databinding.FragmentOnboardingBannerBinding
import com.ssafy.guseul.presentation.uimodel.onboarding.OnBoardingBannerModel

class OnBoardingBannerFragment : Fragment() {

    private var bannerPosition = -1
    private lateinit var binding: FragmentOnboardingBannerBinding
    private val bannerContentList by lazy { resources.getStringArray(R.array.content_on_boarding_banner) }

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

        val tempData = listOf(
            OnBoardingBannerModel(R.drawable.bg_onboarding_banner_1, bannerContentList[0]),
            OnBoardingBannerModel(R.drawable.bg_onboarding_banner_2, bannerContentList[1]),
            OnBoardingBannerModel(R.drawable.bg_onboarding_banner_3, bannerContentList[2])
        )

        bannerPosition = arguments?.getInt(BUNDEL_KEY_ON_BOARDING_BANNER_POSITION)!!
        with(tempData[bannerPosition]) {
            binding.ivLoginBanner.setImageResource(img)
            binding.tvBannerContent.text = message
        }
    }
}