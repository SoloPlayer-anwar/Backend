package com.example.kasir

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.kasir.ui.LoginActivity
import com.example.kasir.ui.home.BottomNavigationActivity

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
            finishAffinity()
        },5000)

    }
}