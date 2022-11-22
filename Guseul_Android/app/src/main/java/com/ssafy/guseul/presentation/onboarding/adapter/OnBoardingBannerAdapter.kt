package com.ssafy.guseul.presentation.onboarding.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssafy.guseul.common.util.Constants.BUNDEL_KEY_ON_BOARDING_BANNER_POSITION
import com.ssafy.guseul.presentation.onboarding.banner.OnBoardingBannerFragment

class OnBoardingBannerAdapter(
    fragment: Fragment,
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return OnBoardingBannerFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDEL_KEY_ON_BOARDING_BANNER_POSITION, position)
            }
        }
    }
}