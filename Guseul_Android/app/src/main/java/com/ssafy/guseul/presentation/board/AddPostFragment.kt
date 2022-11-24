package com.ssafy.guseul.presentation.board

import android.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.showSnackBarMessage
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
            if (category == "카테고리") {
                it.showSnackBarMessage("카테고리를 반드시 입력해주세요.")
            } else {
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
        }
        binding.layoutCategory.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setAdapter(arrayAdapter) { dialog, which ->
                binding.tvCategory.text = arrayAdapter.getItem(which)
                binding.tvCategory.setTextColor(
                    ResourcesCompat.getColor(
                        resources,
                        R.color.nero,
                        null
                    )
                )
                viewModel.makePost(category = which + 1)
                dialog.dismiss()
            }
            dialogBuilder.show()
        }

        binding.etContent.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tvTextCount.text = "${s?.length}/200"
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun initAdapter() {
        if (arrayAdapter.isEmpty) {
            arrayAdapter.add("택시")
            arrayAdapter.add("맛집")
            arrayAdapter.add("공동구매")
            arrayAdapter.add("잡담")
        }
    }

}