package com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels

import android.os.Build
import com.exclr8.n1corporate.BuildConfig

data class LoginDetails(var userName: String,
                        var password: String,
                        var oSName: String = "Android",
                        var oSVersion: String = Build.VERSION.INCREMENTAL,
                        var appVersion: String = BuildConfig.VERSION_NAME,
                        var deviceBrand: String = Build.BRAND,
                        var deviceModel: String = Build.MODEL)