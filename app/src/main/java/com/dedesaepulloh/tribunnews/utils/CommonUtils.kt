package com.dedesaepulloh.tribunnews.utils

import android.content.Context
import android.widget.Toast
import com.dedesaepulloh.tribunnews.utils.Constants.DATE_TIME_FORMAT_DEFAULT
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object CommonUtils {

    private val localeIndonesia = Locale("in", "ID")

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun String.changeDateFormat(newFormat: String): String {
        val currentDateFormat = SimpleDateFormat(DATE_TIME_FORMAT_DEFAULT, localeIndonesia)
        currentDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val newDateFormat = SimpleDateFormat(newFormat, localeIndonesia)
        val date = currentDateFormat.parse(this)!!
        return newDateFormat.format(date)
    }




}