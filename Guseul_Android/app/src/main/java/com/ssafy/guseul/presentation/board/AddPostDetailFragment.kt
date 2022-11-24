package com.ssafy.guseul.presentation.board

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
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
        initForm()
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.M)
    fun initListener() {
        binding.btnArrowLeft.setOnClickListener {
            popBackStack()
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.makePost(
                departures = binding.etDeparture.text.toString(),
                arrivals = binding.etArrival.text.toString(),
                headCount = if (binding.etHeadCount.text.isNotEmpty()) {binding.etHeadCount.text.toString().toInt()} else 0,
                openChattingUrl = binding.etOpenChatting.text.toString(),
                time = binding.tvDate.toString() + " " + binding.tvTime.toString(),
                productUrl = binding.etProductUrl.text.toString(),
                location = binding.etLocation.text.toString(),
                product = binding.etProduct.text.toString(),
                price = if (binding.etPrice.text.isNotEmpty()) {binding.etPrice.text.toString().toInt()} else 0
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
                binding.tvDate.text = " ${datePicker.year}-${datePicker.month}-${datePicker.dayOfMonth}"
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
                binding.tvTime.text = " ${timePicker.hour}시 ${timePicker.minute}분"
                dialog.dismiss()
            }
            dialogBuilder.show()
        }
    }

    fun initForm() {
        viewModel.post.observe(viewLifecycleOwner) {
            when (it.category) {
                1 -> getTaxiForm()
                2 -> getRestaurantForm()
                3 -> getBuyForm()
                4 -> getTalkForm()
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
            layoutDate.visibility = View.GONE
        }
    }

    fun getTalkForm() {
        binding.apply {
            layoutDeparture.visibility = View.GONE
            layoutArrival.visibility = View.GONE
            layoutTime.visibility = View.GONE
            layoutDate.visibility = View.GONE
            layoutHeadCount.visibility = View.GONE
            layoutProduct.visibility = View.GONE
            layoutLocation.visibility = View.GONE
            layoutPrice.visibility = View.GONE
            layoutProductUrl.visibility = View.GONE
            layoutOpenChatting.visibility = View.GONE
        }
    }
}