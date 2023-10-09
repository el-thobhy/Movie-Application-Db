package com.elthobhy.movieapplicatiodb.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.elthobhy.movieapplicatiodb.databinding.ActivitySplashBinding
import com.elthobhy.movieapplicatiodb.ui.movie.MainActivity
import com.elthobhy.movieapplicationdb.core.utils.Constants

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToMain()
    }

    private fun goToMain() {
        Handler(Looper.getMainLooper())
            .postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            }, Constants.DELAY)
    }
}