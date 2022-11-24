package com.ssafy.guseul.presentation.board.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.ssafy.guseul.databinding.ContentMessageDialogBinding
import com.ssafy.guseul.databinding.DialogBoardDeleteBinding

class DeleteBoardDialog(
    context: Context,
    private val deleteBoard: () -> Unit
) : Dialog(context) {

    private lateinit var binding: ContentMessageDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentMessageDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvDialogTitle.text = "게시글을 삭제하시겠어요?"
        binding.tvDialogContent.text = "삭제하면 게시글 기록을 읽어버려요!"
        binding.btnPositive.text = "삭제"
        binding.btnNegative.text = "취소"

        addButtonEventListener()
    }

    private fun addButtonEventListener() {
        binding.btnPositive.setOnClickListener {
            deleteBoard.invoke()
            dismiss()
        }

        binding.btnNegative.setOnClickListener {
            dismiss()
        }
    }
}