package com.example.appointmentsystem.util

import android.app.AlertDialog
import android.content.Context
import com.example.appointmentsystem.R

class InfoDialogs {

    companion object{

        fun showInputError(c:Context){
            val builder = AlertDialog.Builder(c)
            builder.setMessage("Check required fields")
            builder.setTitle("Warning")
            builder.create().show()
        }

    }
}