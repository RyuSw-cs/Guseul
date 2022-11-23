package com.ssafy.guseul.presentation.join

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.Constants.NO_USER_ID
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.common.util.showToastMessage
import com.ssafy.guseul.databinding.FragmentJoinBinding
import com.ssafy.guseul.presentation.MainActivity
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import com.ssafy.guseul.presentation.join.dialog.JoinCancelDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {

    private val joinViewModel : JoinViewModel by viewModels()

    override fun initView() {
        initButtonEvent()

        startObserveJoinViewModel()
    }

    private fun initButtonEvent() {
        binding.btnBack.setOnClickListener {
            val dialog = JoinCancelDialog(requireContext()) {
                navController.popBackStack()
            }
            dialog.show()
        }

        binding.btnStart.setOnClickListener {
            //서버 통신
            val inputAddress = binding.etLocation.text.toString()
            val inputNickname = binding.etNickname.text.toString()

            if (validationAdditionalInfo(inputNickname, inputAddress)) {
                joinViewModel.editUserAdditionalInfo(inputNickname, inputAddress)
            }else{
                requireContext().showToastMessage("빈 값있음")
            }
        }
    }

    private fun startObserveJoinViewModel(){
        joinViewModel.userEntity.observe(viewLifecycleOwner){ response ->
            when(response){
                is ViewState.Loading -> {
                    requireContext().setLoadingDialog(true)
                }
                is ViewState.Success -> {
                    requireContext().setLoadingDialog(false)
                    val result = response.value

                    if(result?.userId != NO_USER_ID){
                        requireContext().showToastMessage("회원 가입 성공 $result")
                        activity?.finish()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }
                }
                is ViewState.Error -> {
                    requireContext().setLoadingDialog(false)
                }
            }
        }
    }

    private fun validationAdditionalInfo(nickname : String, address : String): Boolean {
        return nickname.isNotEmpty() && address.isNotEmpty()
    }
}