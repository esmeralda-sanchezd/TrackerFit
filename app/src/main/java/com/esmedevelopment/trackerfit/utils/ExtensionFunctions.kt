package com.esmedevelopment.trackerfit.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Date.toDisplayFormat(): String {
    val dateFormat: DateFormat = SimpleDateFormat("MM/dd/YYYY")
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return dateFormat.format(this)
}