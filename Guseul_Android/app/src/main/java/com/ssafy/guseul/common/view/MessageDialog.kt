package com.ssafy.guseul.common.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ContentMessageDialogBinding
import org.w3c.dom.Attr

class MessageDialog : ConstraintLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initView()
    }

    private var title: TextView? = null
    private var content: TextView? = null

    private var pButton: Button? = null
    private var nButton: Button? = null

    private fun initView() {
        val service = Context.LAYOUT_INFLATER_SERVICE
        val li = context.getSystemService(service) as LayoutInflater
        val v = li.inflate(R.layout.content_message_dialog, this, false)
        addView(v)

        title = findViewById(R.id.tv_dialog_title)
        content = findViewById(R.id.tv_dialog_content)
        pButton = findViewById(R.id.btn_positive)
        nButton = findViewById(R.id.btn_negative)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageDialogAttributes)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageDialogAttributes, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {

        title?.text = typedArray.getString(R.styleable.MessageDialogAttributes_dialogTitle) as String
        content?.text =
            typedArray.getString(R.styleable.MessageDialogAttributes_dialogContent) as String
        pButton?.text = typedArray.getString(R.styleable.MessageDialogAttributes_positiveContent) as String
        nButton?.text = typedArray.getString(R.styleable.MessageDialogAttributes_negativeContent) as String

        typedArray.recycle()
    }

    fun setPositiveButtonClickListener(listener: (View) -> Unit) {
        pButton?.setOnClickListener(listener)
    }

    fun setNegativeButtonClickListener(listener: (View) -> Unit) {
        nButton?.setOnClickListener(listener)
    }
}