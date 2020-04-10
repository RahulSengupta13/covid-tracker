package com.rahulsengupta.core.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

fun String.getFormattedDate(): String {
    val fromFormatter = DateTimeFormatter.ofPattern("M/d/yy")
    val localDate = LocalDate.parse(this, fromFormatter)
    val formatter = DateTimeFormatter.ofPattern("dd MMM, eee, yyyy", Locale.getDefault())
    return localDate.format(formatter)
}