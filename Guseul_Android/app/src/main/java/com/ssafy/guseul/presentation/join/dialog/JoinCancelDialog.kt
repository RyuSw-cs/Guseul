package com.ssafy.guseul.presentation.join.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.ssafy.guseul.databinding.DialogJoinCancelBinding

class JoinCancelDialog(
    context: Context,
    private val backPress: () -> Unit
) : Dialog(context) {

    private lateinit var binding: DialogJoinCancelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogJoinCancelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtonEvent()
    }

    private fun initButtonEvent() {
        binding.dialogJoinCancel.setNegativeButtonClickListener {
            dismiss()
            backPress.invoke()
        }
        binding.dialogJoinCancel.setPositiveButtonClickListener {
            dismiss()
        }
    }
}