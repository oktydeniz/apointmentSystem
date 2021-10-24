package com.example.appointmentsystem.util

import android.content.Context
import com.example.appointmentsystem.room.AppointmentDatabase

fun Context.loadJSONFromAssets(fileName: String): String {
    return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
        reader.readText()
    }
}
