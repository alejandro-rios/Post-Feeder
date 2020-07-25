package com.alejandrorios.gpostfeeder.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alejandrorios.gpostfeeder.R
import com.alejandrorios.gpostfeeder.presentation.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alejandro Rios on 7/25/20
 */
fun formatToDate(date: String): String {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(date.toLong())
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
        null,
        message,
        null
    ).createProgressDialog()
}

class AlertDialogHelper(
    private val context: Context,
    private val title: CharSequence?,
    private val message: CharSequence?,
    private val icon: Int?
) {
    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)

    fun create(): AlertDialog {
        builder.apply {
            icon?.let {
                setIcon(it)
            }
            setTitle(title)
            setMessage(message)
            setCancelable(false)
        }

        return builder.create()
    }

    fun setOkButton(func: (() -> Unit)? = null) {
        builder.setPositiveButton(android.R.string.ok) { dialogInterface, _ ->
            func?.invoke()
            dialogInterface.dismiss()
        }
    }

    fun setCancelButton(func: (() -> Unit)) {
        builder.setPositiveButton(android.R.string.cancel) { dialogInterface, _ ->
            func.invoke()
            dialogInterface.dismiss()
        }
    }

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
