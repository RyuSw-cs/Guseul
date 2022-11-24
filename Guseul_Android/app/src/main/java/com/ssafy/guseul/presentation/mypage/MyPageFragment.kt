package com.ssafy.guseul.presentation.mypage

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.databinding.FragmentMypageBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val userViewModel by viewModels<UserViewModel>()
    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun initView() {
        //getCount()
        initData()
        initListener()
    }

//    fun getCount() {
//        historyViewModel.boardEntity.observe(viewLifecycleOwner) {
//            when (it) {
//                is ViewState.Success -> {
//
//                }
//                else -> {
//                    Log.d("getCount", "getCount: ")
//                    // Do Nothing
//                }
//            }
//        }
//        historyViewModel.getUserHistory(userViewModel.userId)
//    }

    fun initData() {
        userViewModel.userEntity.observe(viewLifecycleOwner) {
            binding.tvProfileName.text = "${it.value?.nickname} 님"
            binding.tvProfileAddress.text = it.value?.address
        }
        userViewModel.count.observe(viewLifecycleOwner) {
            binding.tvStamp.text = "구슬을 벌써 ${it}개 모았어요!"
        }
        userViewModel.getUserInfo()

    }
    fun initListener() {
        binding.apply {
            tvHistory.setOnClickListener { navigate(MyPageFragmentDirections.actionMyPageFragmentToMyHistoryFragment(userViewModel.userId)) }
            tvModify.setOnClickListener { navigate(MyPageFragmentDirections.actionMyPageFragmentToModifyInfoFragment(userViewModel.userId)) }
        }
    }
}