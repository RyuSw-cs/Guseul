package com.ssafy.guseul.common.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.ssafy.guseul.common.view.LoadingDialog

fun Context.showToastMessage(message : String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.setLoadingDialog(flag: Boolean) {
    if (flag) LoadingDialog.getLoadingDialogInstance(this)?.show()
    else LoadingDialog.getLoadingDialogInstance(this)?.dismiss()
}

fun View.showSnackBarMessage(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}