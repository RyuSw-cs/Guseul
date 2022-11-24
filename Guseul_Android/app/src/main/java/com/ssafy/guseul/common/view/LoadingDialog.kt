package com.ssafy.guseul.common.view

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.ssafy.guseul.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setCanceledOnTouchOutside(false)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)
    }

    companion object {
        private var loadingDialog: Dialog? = null
        private var currentActivityName: String? = null

        @JvmStatic
        fun getLoadingDialogInstance(activity: Activity): Dialog? {
            loadingDialog = if (loadingDialog == null) {
                LoadingDialog(activity)
            }else{
                if(currentActivityName == activity.javaClass.name){
                    loadingDialog
                }else{
                    currentActivityName = activity.javaClass.name
                    LoadingDialog(activity)
                }
            }
            return loadingDialog
        }
    }
}