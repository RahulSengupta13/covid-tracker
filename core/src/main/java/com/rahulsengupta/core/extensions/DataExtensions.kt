package com.rahulsengupta.core.extensions

import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.util.*

fun String.getFormattedDateFromShortPattern(): String {
    val fromFormatter = DateTimeFormatter.ofPattern("M/d/yy")
    val localDate = LocalDate.parse(this, fromFormatter)
    val formatter = DateTimeFormatter.ofPattern("dd MMM, eee, yyyy", Locale.getDefault())
    return localDate.format(formatter)
}

fun String.getFormattedDateFromUTCTimestamp(): String {
    val f = DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        .appendPattern("[.SSSSSSS][.SSSSSS][.SSS]")
        .appendOffset("+HH:mm", "Z")
        .toFormatter()
    val localDate = LocalDateTime.parse(this, f)
    val formatter = DateTimeFormatter.ofPattern("hh:mm a, dd MMM, eee, yyyy", Locale.getDefault())
    return localDate.format(formatter)
}

fun String.getLongFromTimeStamp(): Long {
    val f = DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
        .appendPattern("[.SSSSSSS][.SSSSSS][.SSS]")
        .appendOffset("+HH:mm", "Z")
        .toFormatter()
    val localDate = LocalDateTime.parse(this, f)
    return localDate.toInstant(ZoneOffset.UTC).toEpochMilli()
}

fun Long.toFormattedLocalDateTime(): String {
    val localDate = Instant.ofEpochMilli(this).atOffset(ZoneOffset.UTC).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("hh:mm a, dd MMM", Locale.getDefault())
    return formatter.format(localDate)
}