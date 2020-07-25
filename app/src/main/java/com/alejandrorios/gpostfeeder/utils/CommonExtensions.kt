package com.alejandrorios.gpostfeeder.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.presentation.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alejandro Rios on 7/25/20
 */
fun formatToDate(date: String): String {
    return try {
        val sdf = SimpleDateFormat(MM_DD_YYYY_FORMAT, Locale.ROOT)
        val netDate = Date(date.toLong() * 1000)

        sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}

fun AppCompatActivity.progressDialog(
    message: String
): AlertDialog {
    return AlertDialogHelper(
        this,
        message
    ).createProgressDialog()
}

class AlertDialogHelper(
    private val context: Context,
    private val message: CharSequence?
) {
    fun createProgressDialog(): AlertDialog {
        val builder = AlertDialog.Builder(context)
        val dialogView: View? =
            (context as BaseActivity).layoutInflater.inflate(R.layout.layout_progress_dialog, null)
        val tvProgressMessage: TextView? = dialogView?.findViewById(R.id.tvProgressMessage)
        tvProgressMessage?.text = message ?: EMPTY

        builder.apply {
            setView(dialogView)
            setCancelable(false)
        }

        return builder.create()
    }
}

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

fun AppCompatEditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}
