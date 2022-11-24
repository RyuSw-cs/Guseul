package com.ssafy.guseul.common.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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

fun EditText.hideKeyboard(){
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}