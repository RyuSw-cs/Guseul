package com.ssafy.guseul.common.view

import android.widget.TextView
import androidx.databinding.BindingAdapter

object CommonBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = arrayOf("phone", "address"))
    fun setPlaceInfo(view: TextView, phone: String, address: String) {
        val outputPhone = phone.ifEmpty {
            "전화번호가 없습니다."
        }
        view.text = "$outputPhone | $address"
    }
}