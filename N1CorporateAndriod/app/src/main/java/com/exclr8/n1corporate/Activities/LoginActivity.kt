package com.exclr8.n1corporate.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.exclr8.n1corporate.AsyncTasks.ClearDatabaseAsync
import android.content.Intent
import com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels.LoginDetails
import com.exclr8.n1corporate.APIHelpers.Login.LoginAPI
import android.view.View
import com.exclr8.n1corporate.APIHelpers.Login.ImageKeyLoaderAPI
import com.exclr8.n1corporate.APIHelpers.Login.ProfileAPI
import com.exclr8.n1corporate.Helpers.*
import com.exclr8.n1corporate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this)
        ClearDatabaseAsync(this).clear()

        binding.btnlogin.setOnClickListener {
            if (binding.txteusername.text.toString() != "") {
                if (binding.txtepassworrd.text.toString() != "") {
                    val details = LoginDetails(binding.txteusername.text.toString(), binding.txtepassworrd.text.toString())

                    //Save login details
                    PreferenceHelper().setUsername(binding.txteusername.text.toString())
                    PreferenceHelper().setPassword(binding.txtepassworrd.text.toString())
                    RunLogin(details)
                } else {
                    binding.txtepassworrd.error = "Invalid Password"
                }
            } else {
                binding.txteusername.error = "Invalid UserName"
            }
            binding.txteusername.setText("")
            binding.txtepassworrd.setText("")
        }

        binding.btnForgotPassword.setOnClickListener {
            val forgotPassword = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            forgotPassword.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            finish()
            startActivity(forgotPassword)
        }
    }


    fun RunLogin(details: LoginDetails?) {
        binding.layoutProgressDialog.visible()
        Helper().hideKeyboard(this)


        LoginAPI(details!!).post{ Success, Offline, Exception ->
            if (Success){
                if(!Offline){
                    ProfileAPI(this).get{Success, Offline, Exception ->
                        if (Success){
                            if(!Offline){
                                this@LoginActivity.runOnUiThread{
                                    binding.cardDownloadImages.visible()
                                    Helper().animateImageLoading(binding.imageLoginLoadingTruck, this)
                                    downloadImages(){ done ->
                                        if (done) {
                                            val home = Intent(this, DashboardActivity::class.java)
                                            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                            this.startActivity(home)
                                            this.finish()
                                        }
                                    }
                                }
                            }else{
                                State().showOffline(this, this)
                            }
                        }else{
                            this.runOnUiThread {
                                this.binding.layoutProgressDialog.gone()
                                State().showCustomErrorToastOnUIThread(this, this, Exception)
                            }
                        }
                    }
                }else{
                    State().showOffline(this, this)
                }
            }else{
                this.runOnUiThread {
                    this.binding.layoutProgressDialog.gone()
                    State().showCustomErrorToastOnUIThread(this, this, Exception)
                }
            }
        }
    }

    fun downloadImages(callback: (Boolean) -> Unit){
        ImageKeyLoaderAPI().get {Success, Offline, Exception, ImageKeyList ->
            binding.progLoginDownload.isIndeterminate = true
            if (ImageKeyList != null) {
                ImageLoader().getCache(this, ImageKeyList, binding.txtvLoginDownloadProgress) { done ->
                    if (done) {
                        callback(true)
                    }
                }
            }
        }
    }
}