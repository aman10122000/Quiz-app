package com.example.quizonline

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        // Fade in animation
        val fadeInAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(1000)
        fadeInAnimator.addUpdateListener { animation ->
            val main = findViewById<ConstraintLayout>(R.id.main)
            main.alpha = animation.animatedValue as Float
        }
        fadeInAnimator.start()

        // Fade out animation
        val fadeOutAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(1000)
        fadeOutAnimator.startDelay = 2000 // Delay before starting fade out
        fadeOutAnimator.addUpdateListener { animation ->
            val main = findViewById<ConstraintLayout>(R.id.main)
            main.alpha = animation.animatedValue as Float
        }
        fadeOutAnimator.start()

        // Navigate to MainActivity after fade out animation completes
        fadeOutAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                startActivity(Intent(this@SplashScreen, Login::class.java))
                finish()
            }
        })
    }
}