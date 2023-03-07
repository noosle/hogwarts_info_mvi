package ru.noosle.harry_potter_mvi.ui.main

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