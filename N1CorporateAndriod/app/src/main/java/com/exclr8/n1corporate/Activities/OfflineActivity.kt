package com.exclr8.n1corporate.Activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.exclr8.n1corporate.R

class OfflineActivity : AppCompatActivity() {
    lateinit var close_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_screen)
        close_btn = findViewById<View>(R.id.offline_close_btn) as Button
        close_btn.setOnClickListener { finishAndRemoveTask() }
    }

}