package com.exclr8.n1corporate.APIHelpers.Authorization

import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.AuthenticationResponse
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.BuildConfig
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AuthenticateAPI {
    var json: JSONObject? = null
    private val client = OkHttpClient()

    fun post(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit) {
        val formbody: RequestBody = FormBody.Builder()
            .add("ApplicationKey", URLs().appkey)
            .add("AppVersion", BuildConfig.VERSION_NAME)
            .add("OSName", "Android")
            .build()

        val request = Request.Builder()
            .url(URLs().authURL)
            .post(formbody)
            .build()
        try {
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        json = JSONObject(response.body()!!.string())
                        val resp =
                            Gson().fromJson(json.toString(), AuthenticationResponse::class.java)
                        if (!resp.Offline) {
                            PreferenceHelper().setAPPKEYNAME(resp.TokenKeyName)
                            PreferenceHelper().setAPPKEY(resp.TokenKey)
                            callback(true, false, null)
                        }
                    } else {
                        callback(false, false, GlobalValue.genericError.message)
                    }
                }
            })
        } catch (e: Exception) {
            callback(false, false, GlobalValue.genericError.message)
        }
    }
}