package com.example.appointmentsystem.views.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.appointmentsystem.OpeningActivity
import com.example.appointmentsystem.R
import com.example.appointmentsystem.util.SharedPreferenceManager

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.singOut){
            val manager = SharedPreferenceManager.invoke(applicationContext)
            manager.setIsDoctor(false)
            manager.setUserNumber("0")
            manager.setUserName("null")
            manager.saveUser(0)
            val intent = Intent(this,OpeningActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}