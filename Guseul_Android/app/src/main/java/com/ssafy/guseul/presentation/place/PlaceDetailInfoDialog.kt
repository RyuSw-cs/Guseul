package com.ssafy.guseul.presentation.place

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.DialogMapInfoBinding
import com.ssafy.guseul.domain.entity.place.PlaceEntity
import net.daum.mf.map.api.MapPoint
import kotlin.math.*

class PlaceDetailInfoDialog(
    private val currentLocation: MapPoint.GeoCoordinate,
    private val placeInfo: PlaceEntity
) : BottomSheetDialogFragment(R.layout.dialog_map_info) {

    private lateinit var binding: DialogMapInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMapInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
    }

    private fun initContent() {
        binding.placeEntity = placeInfo
        binding.tvDistanceToCurrentLocation.text = setDistance()
    }

    private fun getDistance(): Int {

        val R = 6372.8 * 1000

        val dLat = Math.toRadians(placeInfo.latitude - currentLocation.latitude)
        val dLon = Math.toRadians(placeInfo.longitude - currentLocation.longitude)
        val a =
            sin(dLat / 2).pow(2.0) + sin(dLon / 2).pow(2.0) * cos(Math.toRadians(placeInfo.latitude)) * cos(
                Math.toRadians(currentLocation.latitude)
            )
        val c = 2 * asin(sqrt(a))

        return (R * c).toInt()
    }

    private fun setDistance(): String {
        val distance = getDistance()
        return if (distance > 1000) "현 위치로부터 ${distance.toDouble() / 100.0}km 떨어져있어요!"
        else "현 위치로부터 ${distance}m 떨어져있어요!"
    }

    private fun initCallButtonEvent() {

    }

    private fun initFindLocationButtonEvent() {

    }
}