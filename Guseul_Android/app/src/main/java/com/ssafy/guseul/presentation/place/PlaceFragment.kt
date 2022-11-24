package com.ssafy.guseul.presentation.place

import android.Manifest
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.common.util.showSnackBarMessage
import com.ssafy.guseul.common.util.showToastMessage
import com.ssafy.guseul.databinding.FragmentPlaceBinding
import com.ssafy.guseul.domain.entity.place.AddressEntity
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.AndroidEntryPoint
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

//권한 추가 해야함
@AndroidEntryPoint
class PlaceFragment : BaseFragment<FragmentPlaceBinding>(R.layout.fragment_place),
    MapView.CurrentLocationEventListener {

    private lateinit var map: MapView
    private lateinit var currentAddress: AddressEntity

    private val requestPermissionLauncher = initPermissionLauncher()
    private val categorySet = mutableSetOf<String>()
    private val viewModel by viewModels<PlaceViewModel>()

    private var currentLocation: MapPoint.GeoCoordinate? = null
    private var reLocationClickCount = 0
    private var currentLocationFlag = false


    override fun initView() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun initPermissionLauncher() =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                initMap()

                // 버튼 이벤트 추가
                addCategoryButtonEvent()
                addEditTextEditorListener()
                addReLocationButtonEvent()

                // 옵저빙 시작
                startObservePlaceViewModel()

                // 음식점 카테고리 추가
                categorySet.add("FD6")
            } else {
                binding.root.showSnackBarMessage("지도 기능을 사용하기 위해선 권한을 허용해주세요.")
                navController.popBackStack()
            }
        }

    private fun initMap() {
        map = MapView(requireActivity())
        map.setCurrentLocationEventListener(this)
        binding.lyMap.addView(map)

        map.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    private fun startObservePlaceViewModel() {
        viewModel.currentAddress.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {
                    requireActivity().setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    requireActivity().setLoadingDialog(false)
                    currentAddress = response.value!!
                }
                is ViewState.Error -> {
                    requireActivity().setLoadingDialog(false)
                }
            }
        }
        viewModel.markerList.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    requireActivity().setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    requireActivity().setLoadingDialog(false)
                }
                is ViewState.Error -> {
                    requireActivity().setLoadingDialog(false)
                }
            }
        }
    }

    private fun addCategoryButtonEvent() {
        binding.run {
            //first parameter: button, second parameter: isChecked
            btnMapRestaurant.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add("FD6")
                else categorySet.remove("FD6")

                //서버 연동 추가
            }
            btnMapCafe.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add("CE7")
                else categorySet.remove("CE7")
            }
            btnMapHospital.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add("HP8")
                else categorySet.remove("HP8")
            }
            btnMapMart.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    categorySet.add("MT1")
                    categorySet.add("CS2")
                } else {
                    categorySet.remove("MT1")
                    categorySet.remove("CS2")
                }
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
                if (binding.etSearch.text.toString().isEmpty()) {
                    //디폴트 주소로 검색
                    currentLocationFlag = false
                    viewModel.getMarkerListByKeyword(
                        currentAddress.address3depthName!!,
                        currentLocation?.longitude!!,
                        currentLocation?.latitude!!,
                        categorySet
                    )
                } else {
                    //키워드로 검색
                    viewModel.getMarkerListByKeyword(
                        binding.etSearch.text.toString(),
                        currentLocation?.longitude!!,
                        currentLocation?.latitude!!,
                        categorySet
                    )
                }
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
        if (!currentLocationFlag) {
            viewModel.getCurrentAddress(currentLocation?.longitude!!, currentLocation?.latitude!!)
            //만약 현재 위치가 계속 변한다면
            if (map.currentLocationTrackingMode != MapView.CurrentLocationTrackingMode.TrackingModeOff) {
                currentLocationFlag = true
            }
        }
    }

    override fun onCurrentLocationDeviceHeadingUpdate(p0: MapView?, p1: Float) {

    }

    override fun onCurrentLocationUpdateFailed(p0: MapView?) {

    }

    override fun onCurrentLocationUpdateCancelled(p0: MapView?) {

    }

    companion object {
        private const val TAG = "MapFragment"
    }
}