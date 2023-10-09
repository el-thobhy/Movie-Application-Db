package com.elthobhy.movieapplicatiodb.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.elthobhy.movieapplicatiodb.databinding.LayoutDialogErrorBinding

fun showDialogError(context: Context, message: String? = null): AlertDialog {
    val dialogView = LayoutDialogErrorBinding.inflate(LayoutInflater.from(context))
    dialogView.tvMessage.text = message
    val alert = AlertDialog
        .Builder(context)
        .setView(dialogView.root)
        .setCancelable(true)
        .create()
    alert.window?.decorView?.setBackgroundResource(android.R.color.transparent)
    return alert
}