package com.example.shyariapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.coroutines.android.HandlerDispatcher


class splace : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splace)
        //old code
     //  Handler().postDelayed({

       //},3000)

        // new code
        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(this,StartActivity::class.java))
            finish()

        }, 1000)

    }
}