package com.exclr8.n1corporate.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exclr8.n1corporate.APIHelpers.Authorization.AppConfigurationAPI
import com.exclr8.n1corporate.APIHelpers.Authorization.ApplicationPathAPI
import com.exclr8.n1corporate.APIHelpers.Authorization.AuthenticateAPI
import com.exclr8.n1corporate.APIHelpers.Login.LoginAPI
import com.exclr8.n1corporate.APIHelpers.Login.ProfileAPI
import com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels.LoginDetails
import com.exclr8.n1corporate.AsyncTasks.LogoutAsync
import com.exclr8.n1corporate.BuildConfig
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.Helpers.State
import com.exclr8.n1corporate.databinding.ActivityLoadingScreenBinding

open class LoadingActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoadingScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        RoomDB.destroyDataBase()
        PreferenceHelper().initialize(this)

        Helper().animateImageLoading(binding.loadingTruck, this)
        if (PreferenceHelper().getAppVersionValue() != BuildConfig.VERSION_CODE.toString()) {
            PreferenceHelper().clearPreferences()
        }
    }

    override fun onResume() {
        super.onResume()
        ApplicationPathAPI().post{ Success, Offline, Exception ->
            if (Success){
                if(!Offline){
                    AuthenticateAPI().post{ Success, Offline, Exception ->
                        if (Success){
                            if(!Offline){
                                AppConfigurationAPI().get{ Success, Offline, Exception ->
                                    if (Success){
                                        if(!Offline){
                                            PreferenceHelper().setAppVersionValue(BuildConfig.VERSION_CODE.toString())
                                            if (PreferenceHelper().LoginDetaislExists()) {
                                                LogoutAsync(this).execute {
                                                    loginUser()
                                                }
                                            } else {
                                                val login = Intent(this, LoginActivity::class.java)
                                                login.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                                finish()
                                                this.startActivity(login)
                                            }
                                        }else{
                                            State().showOffline(this, this)
                                        }
                                    }else{
                                        State().showOutdated(this, this)
                                    }
                                }
                            }else{
                                State().showOffline(this, this)
                            }
                        }else{
                            State().showOutdated(this, this)
                        }
                    }
                }else{
                    State().showOffline(this, this)
                }
            }else{
                State().showOutdated(this, this)
            }
        }
    }

    fun loginUser() {
        val details = LoginDetails(PreferenceHelper().getUsername()!!, PreferenceHelper().getPassword()!!)
        LoginAPI(details).post { Success, Offline, Exception ->
            if (Success){
                if(!Offline) {
                    ProfileAPI(this).get() { Success, Offline, Exception ->
                        if (Success){
                            if (!Offline){
                                val home = Intent(this, DashboardActivity::class.java)
                                home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                                this.startActivity(home)
                                this.finish()
                            }
                        }else{
                            val login = Intent(this, LoginActivity::class.java)
                            login.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            finish()
                            this.startActivity(login)
                        }
                    }
                }
            }else{
                val login = Intent(this, LoginActivity::class.java)
                login.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                finish()
                this.startActivity(login)
            }
        }
    }
}