package com.ssafy.guseul.presentation.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.DialogMapInfoBinding
import com.ssafy.guseul.domain.entity.place.PlaceEntity
import net.daum.mf.map.api.MapPOIItem

class PlaceDetailInfoDialog(private val placeInfo: PlaceEntity) :
    BottomSheetDialogFragment(R.layout.dialog_map_info) {

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
    }

    private fun initContent(){
        binding.placeEntity = placeInfo
    }
}