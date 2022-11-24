package com.ssafy.guseul.presentation.board

import android.app.AlertDialog
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentAddPostBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPostFragment : BaseFragment<FragmentAddPostBinding>(R.layout.fragment_add_post) {

    private val arrayAdapter by lazy {
        ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1)
    }
    private val viewModel by activityViewModels<BoardViewModel>()
    override fun initView() {
        initAdapter()
        initListener()
    }

    fun initListener() {
        binding.btnArrowLeft.setOnClickListener {
            popBackStack()
        }
        binding.btnGoToDetail.setOnClickListener {
            val category = binding.tvCategory.text.toString()
            viewModel.makePost(
                title = binding.etTitle.text.toString(),
                content = binding.etContent.text.toString(),
                category =
                when (category) {
                    "택시" -> 1
                    "맛집" -> 2
                    "공동구매" -> 3
                    "잡담" -> 4
                    else -> 4
                }
            )

            navigate(AddPostFragmentDirections.actionAddPostFragmentToAddPostDetailFragment())
        }
        binding.layoutCategory.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setAdapter(arrayAdapter) { dialog, which ->
                binding.tvCategory.text = arrayAdapter.getItem(which)
                viewModel.makePost(category = which + 1)
                dialog.dismiss()
            }
            dialogBuilder.show()
        }
    }

    fun initAdapter() {
        arrayAdapter.add("택시")
        arrayAdapter.add("맛집")
        arrayAdapter.add("공동구매")
        arrayAdapter.add("잡담")
    }

}