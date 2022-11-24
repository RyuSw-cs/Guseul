package com.ssafy.guseul.presentation.board

import android.app.AlertDialog
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.showSnackBarMessage
import com.ssafy.guseul.databinding.FragmentBoardDetailBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardDetailFragment : BaseFragment<FragmentBoardDetailBinding>(R.layout.fragment_board_detail) {

    private val args by navArgs<BoardDetailFragmentArgs>()
    private val viewModel by activityViewModels<BoardViewModel>()


    override fun initView() {
        setButtonEnabled()
        initListener()
        getData()
    }

    fun initListener() {
        binding.btnDeleteOption.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("게시글을 삭제하시겠습니까?")
                setMessage("삭제하시면 기록을 잃어버려요!")
                setPositiveButton("확인") { dialog, _ ->
                    viewModel.deletePost(args.postId)
                    viewModel.isDeleted.observe(viewLifecycleOwner) {
                        binding.root.showSnackBarMessage(it)
                    }
                    dialog.dismiss()
                    navigate(BoardDetailFragmentDirections.actionBoardDetailFragmentToBoardFragment())
                }
                setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
            }.create().show()
        }
        binding.btnArrowLeft.setOnClickListener {
            popBackStack()
        }
    }

    fun setButtonEnabled() {
        viewModel.isMine.observe(viewLifecycleOwner) {
            binding.btnBoardState.isEnabled = it
        }
        binding.btnBoardState.setOnClickListener {
            viewModel.boardEntity.value?.value?.let {
                entity -> viewModel.makePost(end = entity.end.not())
                viewModel.editPost(entity.postId)
            }
        }
    }

    fun getData() {
        viewModel.getPost(args.postId)
        viewModel.boardEntity.observe(viewLifecycleOwner) {
            binding.boardEntity = it.value

            when(it.value?.category) {
                1 -> {
                    binding.apply {
                        tvTitleCategory.text = "택시"
                        tvCategory.text = "택시"
                    }
                    getTaxiForm()
                }
                2 -> {
                    binding.apply {
                        tvTitleCategory.text = "맛집"
                        tvCategory.text = "맛집"
                    }
                    getRestaurantForm()
                }
                3 -> {
                    binding.apply {
                        tvTitleCategory.text = "공동구매"
                        tvCategory.text = "공동구매"
                    }
                    getBuyForm()
                }
                4 -> {
                    binding.apply {
                        tvTitleCategory.text = "잡담"
                        tvCategory.text = "잡담"
                    }
                    getTalkForm()
                }
            }
        }
    }

    fun getTaxiForm() {
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