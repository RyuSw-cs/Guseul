package com.ssafy.guseul.presentation.board.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.ssafy.guseul.databinding.DialogBoardDeleteBinding

class DeleteBoardDialog(
    context: Context,
    private val deleteBoard: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogBoardDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogBoardDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addButtonEventListener()
    }

    private fun addButtonEventListener() {
        binding.dialogDelete.setPositiveButtonClickListener {
            deleteBoard.invoke()
            dismiss()
        }

        binding.dialogDelete.setNegativeButtonClickListener {
            dismiss()
        }
    }
}