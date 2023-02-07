package com.exclr8.n1corporate.Activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.exclr8.n1corporate.R


class OutdateActivity : AppCompatActivity() {
    lateinit var close_btn: Button
    lateinit var update_btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_outdate_screen)

        close_btn = findViewById<View>(R.id.outdated_close_btn) as Button
        update_btn = findViewById(R.id.outdated_update_btn) as Button

        update_btn.setOnClickListener{
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }
        close_btn.setOnClickListener { finishAndRemoveTask() }
    }
}