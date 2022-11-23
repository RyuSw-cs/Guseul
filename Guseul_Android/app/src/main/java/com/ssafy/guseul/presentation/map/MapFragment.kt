package com.ssafy.guseul.presentation.map

import android.Manifest
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.showSnackBarMessage
import com.ssafy.guseul.databinding.FragmentMapBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

//권한 추가 해야함
@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(R.layout.fragment_map),
    MapView.CurrentLocationEventListener {

    private lateinit var map: MapView

    private val requestPermissionLauncher = initPermissionLauncher()
    private val categorySet = mutableSetOf<Int>()
    private var currentLocation: MapPoint.GeoCoordinate? = null
    private var reLocationClickCount = 0

    override fun initView() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun initPermissionLauncher() = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if(isGranted){
            initMap()

            //버튼 이벤트 추가
            addCategoryButtonEvent()
            addEditTextEditorListener()
            addReLocationButtonEvent()

            //음식점 카테고리 추가
            categorySet.add(1)
        }else{
            binding.root.showSnackBarMessage("지도 기능을 사용하기 위해선 권한을 허용해주세요.")
            navController.popBackStack()
        }
    }

    private fun initMap() {
        map = MapView(requireActivity())
        binding.lyMap.addView(map)

        map.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    private fun addCategoryButtonEvent() {
        binding.run {
            //first parameter: button, second parameter: isChecked
            //after add kakao local api
            btnMapRestaurant.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(1)
                else categorySet.remove(1)
            }
            btnMapCafe.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(2)
                else categorySet.remove(2)
            }
            btnMapHospital.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(3)
                else categorySet.remove(3)
            }
            btnMapMart.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(4)
                else categorySet.remove(4)
            }
        }
    }

    private fun addReLocationButtonEvent() {
        binding.btnReLocation.setOnClickListener {
            //tracking: 0, compass: 1, normal: 2
            reLocationClickCount++
            when (reLocationClickCount % 3) {
                0 -> map.currentLocationTrackingMode =
                    MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
                1 -> map.currentLocationTrackingMode =
                    MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading
                2 -> map.currentLocationTrackingMode =
                    MapView.CurrentLocationTrackingMode.TrackingModeOff
            }
        }
    }

    private fun addEditTextEditorListener() {
        binding.etSearch.setOnEditorActionListener { _, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                //connect kakao local api
            }
            true
        }
    }

    override fun onCurrentLocationUpdate(
        mapView: MapView?,
        updateLocation: MapPoint?,
        accuracy: Float
    ) {
        currentLocation = updateLocation?.mapPointGeoCoord
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {

    }

    companion object{
        private const val TAG = "MapFragment"
    }
}