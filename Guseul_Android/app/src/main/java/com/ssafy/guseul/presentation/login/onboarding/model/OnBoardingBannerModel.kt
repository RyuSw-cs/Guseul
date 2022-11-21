package com.ssafy.guseul.presentation.login.onboarding.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OnBoardingBannerModel(
    val img : Int,
    val message : String
) : Parcelable
