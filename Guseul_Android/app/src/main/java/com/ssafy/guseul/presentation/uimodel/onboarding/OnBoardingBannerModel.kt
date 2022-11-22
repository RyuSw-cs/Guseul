package com.ssafy.guseul.presentation.uimodel.onboarding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class OnBoardingBannerModel(
    val img : Int,
    val message : String
) : Parcelable
