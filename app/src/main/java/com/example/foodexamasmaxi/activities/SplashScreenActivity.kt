package com.example.foodexamasmaxi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.foodexamasmaxi.R

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 4000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val homeIntent = Intent(this@SplashScreenActivity, LogInActivity::class.java)
            startActivity(homeIntent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
