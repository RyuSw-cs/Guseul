package com.ssafy.guseul.presentation.mypage

import androidx.fragment.app.viewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.showSnackBarMessage
import com.ssafy.guseul.databinding.FragmentInfoEditBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.join.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InfoEditFragment : BaseFragment<FragmentInfoEditBinding>(R.layout.fragment_info_edit) {

    //private val args by navArgs<InfoEditFragmentArgs>()
    private val userViewModel by viewModels<UserViewModel>()
    private val joinViewModel by viewModels<JoinViewModel>()


    override fun initView() {
        initData()
        initListener()
    }

    fun initData() {
        userViewModel.userEntity.observe(viewLifecycleOwner) {
            binding.etNickname.setText(it.value?.nickname ?: "")
            binding.etLocation.setText(it.value?.address ?: "")
        }
        userViewModel.getUserInfo()
    }

    fun initListener() {
        binding.btnArrowLeft.setOnClickListener { popBackStack() }

        binding.btnEdit.setOnClickListener {
            val inputAddress = binding.etLocation.text.toString()
            val inputNickname = binding.etNickname.text.toString()

            if (validationAdditionalInfo(inputNickname, inputAddress)) {
                joinViewModel.editUserAdditionalInfo(inputNickname, inputAddress)
                popBackStack()
                binding.root.showSnackBarMessage("프로필 수정이 완료되었습니다.")
            } else {
                binding.root.showSnackBarMessage("빈 칸을 꼭 채워주세요!")
            }
        }
    }

    private fun validationAdditionalInfo(nickname : String, address : String): Boolean {
        return nickname.isNotEmpty() && address.isNotEmpty()
    }

}