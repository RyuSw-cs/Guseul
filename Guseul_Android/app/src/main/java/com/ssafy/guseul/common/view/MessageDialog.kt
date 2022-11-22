package com.ssafy.guseul.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ContentMessageDialogBinding

class MessageDialog : ConstraintLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    private val binding: ContentMessageDialogBinding by lazy {
        ContentMessageDialogBinding.bind(
            LayoutInflater.from(context).inflate(
                R.layout.content_message_dialog, this, false
            )
        )
    }

    private var positiveButtonClickListener: (() -> Unit)? = null
    private var negativeButtonClickListener: (() -> Unit)? = null

    private fun initView() {
        addView(binding.root)
    }

    @SuppressLint("CustomViewStyleable")
    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.messageDialog)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        binding.run {
            tvDialogTitle.text = typedArray.getString(R.styleable.messageDialog_title)
            tvDialogContent.text = typedArray.getString(R.styleable.messageDialog_content)
            btnPositive.text = typedArray.getString(R.styleable.messageDialog_positiveContent)
            btnNegative.text = typedArray.getString(R.styleable.messageDialog_negativeContent)
        }
        typedArray.recycle()
    }

    fun setPositiveButtonClickListener(listener : () -> Unit){
        this.positiveButtonClickListener = listener
    }
    fun setNegativeButtonClickListener(listener : () -> Unit){
        this.negativeButtonClickListener = listener
    }
}