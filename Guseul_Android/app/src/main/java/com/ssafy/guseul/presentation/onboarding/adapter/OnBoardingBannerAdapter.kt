package com.ssafy.guseul.presentation.onboarding.adapter

import com.ssafy.guseul.presentation.uimodel.onboarding.OnBoardingBannerModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.guseul.common.util.Constants.BUNDEL_KEY_ON_BOARDING_BANNER_DATA
import com.ssafy.guseul.presentation.onboarding.banner.OnBoardingBannerFragment

class OnBoardingBannerAdapter(
    fragment: Fragment,
    private val bannerList: List<OnBoardingBannerModel>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = bannerList.size

    override fun createFragment(position: Int): Fragment {
        return OnBoardingBannerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(BUNDEL_KEY_ON_BOARDING_BANNER_DATA, bannerList[position])
            }
        }
    }
}