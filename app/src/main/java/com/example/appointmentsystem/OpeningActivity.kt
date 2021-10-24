package com.example.appointmentsystem

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.appointmentsystem.util.SharedPreferenceManager
import com.example.appointmentsystem.views.app.AppActivity
import com.example.appointmentsystem.views.user.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class OpeningActivity : AppCompatActivity() {
    private lateinit var manager: SharedPreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)
        init()
    }

    private fun init() {
        manager = SharedPreferenceManager(applicationContext)
        object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val currentDate: String =
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                val currentTime = SimpleDateFormat("HH", Locale.getDefault()).format(Date())
                val user = manager.getUser()
                if (user != 0) {
                    val intent = Intent(applicationContext, AppActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)

                }
                finish()
            }
        }.start()
    }


}