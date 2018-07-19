package com.example.zhangyipeng.anwerdemo.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.zhangyipeng.anwerdemo.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({

            val intent = Intent(this@WelcomeActivity, AnswerReadActivity::class.java)
            startActivity(intent)
            finish()

        }, 500)

    }
}