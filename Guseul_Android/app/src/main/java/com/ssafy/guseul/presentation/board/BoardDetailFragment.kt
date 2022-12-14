package com.ssafy.guseul.presentation.board

import android.app.AlertDialog
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.guseul.ApplicationClass
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.showSnackBarMessage
import com.ssafy.guseul.databinding.FragmentBoardDetailBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.board.dialog.DeleteBoardDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardDetailFragment :
    BaseFragment<FragmentBoardDetailBinding>(R.layout.fragment_board_detail) {

    private val args by navArgs<BoardDetailFragmentArgs>()
    private val viewModel by activityViewModels<BoardViewModel>()

    override fun initView() {
        setButtonEnabled()
        initListener()
        getData()
    }

    private fun initListener() {
        binding.ivOptionMenu.setOnClickListener {
            val dialog = DeleteBoardDialog(requireContext()) {
                viewModel.deletePost(args.postId)
                viewModel.isDeleted.observe(viewLifecycleOwner) {
                    binding.root.showSnackBarMessage(it)
                    popBackStack()
                }
            }
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
        }
        binding.btnArrowLeft.setOnClickListener {
            popBackStack()
        }
    }

    private fun setButtonEnabled() {
        viewModel.isMine.observe(viewLifecycleOwner) {
            binding.btnBoardState.isEnabled = it
        }
        binding.btnBoardState.setOnClickListener {
            viewModel.boardEntity.value?.value?.let { entity ->
                viewModel.editPost(entity.postId, entity.end.not())
            }
        }
    }

    private fun getData() {
        viewModel.getPost(args.postId)
        viewModel.boardEntity.observe(viewLifecycleOwner) {
            binding.boardEntity = it.value
            if (it.value?.userId == ApplicationClass.userId) {
                binding.ivOptionMenu.visibility = View.VISIBLE
            }

            when (it.value?.category) {
                1 -> {
                    binding.apply {
                        tvTitleCategory.text = "??????"
                        tvCategory.text = "??????"
                    }
                    getTaxiForm()
                }
                2 -> {
                    binding.apply {
                        tvTitleCategory.text = "??????"
                        tvCategory.text = "??????"
                    }
                    getRestaurantForm()
                }
                3 -> {
                    binding.apply {
                        tvTitleCategory.text = "????????????"
                        tvCategory.text = "????????????"
                    }
                    getBuyForm()
                }
                4 -> {
                    binding.apply {
                        tvTitleCategory.text = "??????"
                        tvCategory.text = "??????"
                    }
                    getTalkForm()
                }
            }
        }
    }

    private fun getTaxiForm() {
        binding.apply {
            layoutProduct.visibility = View.GONE
            layoutLocation.visibility = View.GONE
            layoutPrice.visibility = View.GONE
            layoutProductUrl.visibility = View.GONE
        }
    }

    fun getRestaurantForm() {
        binding.apply {
            layoutDeparture.visibility = View.GONE
            layoutArrival.visibility = View.GONE
            layoutProduct.visibility = View.GONE
            layoutPrice.visibility = View.GONE
            layoutProductUrl.visibility = View.GONE
        }
    }

    fun getBuyForm() {
        binding.apply {
            layoutDeparture.visibility = View.GONE
            layoutArrival.visibility = View.GONE
            layoutTime.visibility = View.GONE
        }
    }

    fun getTalkForm() {
        binding.apply {
            layoutDeparture.visibility = View.GONE
            layoutArrival.visibility = View.GONE
            layoutTime.visibility = View.GONE
            layoutHeadCount.visibility = View.GONE
            layoutProduct.visibility = View.GONE
            layoutLocation.visibility = View.GONE
            layoutPrice.visibility = View.GONE
            layoutProductUrl.visibility = View.GONE
            layoutOpenChatting.visibility = View.GONE
        }
    }
}