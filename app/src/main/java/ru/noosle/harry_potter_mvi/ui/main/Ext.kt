package ru.noosle.harry_potter_mvi.ui.main

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

fun String.formatHogwartsDate(): String {
    return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).parse(this)?.let {
        SimpleDateFormat(
            "dd MMMM yyyy",
            Locale.ENGLISH
        ).format(it)
    } ?: ""
}

fun TextView.setInfoText(body: String, textToCheck: String?, stub: String) {
    this.text = takeIf { !textToCheck.isNullOrEmpty() }?.let { body.format(textToCheck)}?: stub
}