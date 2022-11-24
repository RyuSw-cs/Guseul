package com.ssafy.guseul.presentation.mypage

import androidx.fragment.app.viewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentMypageBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    val viewModel by viewModels<UserViewModel>()

    override fun initView() {
        initData()
        initListener()
    }

    fun initData() {
        viewModel.userEntity.observe(viewLifecycleOwner) {
            binding.tvProfileName.text = "${it.value?.nickname} 님"
            binding.tvProfileAddress.text = it.value?.address
            //historyViewModel.getUserHistory(viewModel.userId)
        }
        viewModel.getUserInfo()
    }
    fun initListener() {
        binding.apply {
            tvHistory.setOnClickListener { navigate(MyPageFragmentDirections.actionMyPageFragmentToMyHistoryFragment(viewModel.userId)) }
            tvModify.setOnClickListener { navigate(MyPageFragmentDirections.actionMyPageFragmentToModifyInfoFragment(viewModel.userId)) }
        }
    }
}