package com.exclr8.n1corporate.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exclr8.n1corporate.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSubmit.setOnClickListener {
            if (validate()){
                val login = Intent(applicationContext, LoginActivity::class.java)
                login.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
                startActivity(login)
            }
        }
    }

    fun validate(): Boolean{
        if (binding.txtEmail.text.isEmpty()){
            binding.txtEmail.setError("This field cannot be empty!")
            return false
        }else {
            if (binding.txtEmail.text.contains('@', true)) {
                binding.txtEmail.setError("Please enter a valid email address.")
                return false
            }
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val login = Intent(applicationContext, LoginActivity::class.java)
        login.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        finish()
        startActivity(login)
    }
}