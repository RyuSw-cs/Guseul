package com.ssafy.guseul.presentation.board

import android.app.AlertDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentAddPostDetailBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostDetailFragment :
    BaseFragment<FragmentAddPostDetailBinding>(R.layout.fragment_add_post_detail) {

    private val viewModel by activityViewModels<BoardViewModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun initView() {
        initListener()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initListener() {
        binding.btnSubmit.setOnClickListener {
            viewModel.makePost(
                departures = binding.etDeparture.text.toString(),
                arrivals = binding.etArrival.text.toString(),
                headCount = binding.etHeadCount.text.toString().toInt(),
                openChattingUrl = binding.etOpenChatting.text.toString()
            )
            viewModel.makePost(time = binding.tvDate.text.toString() + " " + binding.tvTime.text.toString())
            viewModel.isCreated.observe(viewLifecycleOwner) {
                if (it == true) {
                    navigate(AddPostDetailFragmentDirections.actionAddPostDetailFragmentToBoardFragment())
                } else {
                    Toast.makeText(context, "게시글이 정상적으로 생성되지 않았습니다.", Toast.LENGTH_SHORT).show()
                    navigate(AddPostDetailFragmentDirections.actionAddPostDetailFragmentToBoardFragment())
                }
            }
            viewModel.createPost()
        }

        binding.layoutDate.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            val datePicker = DatePicker(requireContext())
            dialogBuilder.setView(datePicker)
            dialogBuilder.setPositiveButton("확인") {
                dialog, _ ->
                binding.tvDate.text = "${datePicker.year}-${datePicker.month}-${datePicker.dayOfMonth}"
                dialog.dismiss()
            }
            dialogBuilder.show()
        }

        binding.layoutTime.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            val timePicker = TimePicker(requireContext())
            dialogBuilder.setView(timePicker)
            dialogBuilder.setPositiveButton("확인") {
                    dialog, _ ->
                binding.tvTime.text = "${timePicker.hour}:${timePicker.minute}"
                dialog.dismiss()
            }
            dialogBuilder.show()
        }

        }
}