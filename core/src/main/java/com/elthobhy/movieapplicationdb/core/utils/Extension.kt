package com.elthobhy.movieapplicationdb.core.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.elthobhy.movieapplicationdb.databinding.LayoutDialogErrorBinding

fun showDialogError(context: Context, message: String? = null): AlertDialog {
    val dialogView = LayoutDialogErrorBinding.inflate(LayoutInflater.from(context))
    dialogView.tvMessage.text = message
    return AlertDialog
        .Builder(context)
        .setView(dialogView.root)
        .setCancelable(true)
        .create()
}