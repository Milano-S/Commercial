package com.exclr8.n1corporate.APIHelpers.Authorization

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.BuildConfig
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.AppPathResponse
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.toAppPathResponse
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ApplicationPathAPI{
    private val client = OkHttpClient()
    private val preferenceHelper = PreferenceHelper()

    fun post(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit) {
        val formbody: RequestBody = FormBody.Builder()
                .add("Version", BuildConfig.VERSION_NAME)
                .add("OSName", "Android")
                .build()

        val request = Request.Builder()
                .url(URLs().appPathURL)
                .post(formbody)
                .build()

        try {
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val resp = response.toAppPathResponse()

                        if (!resp.Offline) {
                            preferenceHelper.setBaseURLString(resp.ApplicationPath!!)
                            preferenceHelper.setAPIBaseURLString(resp.ApiPath!!)
                            callback(true, false, null)
                        } else {
                            callback(true, true, null)
                        }
                    } else {
                        callback(false, false, GlobalValue.genericError.message)
                    }                }

            })
        } catch (e: Exception) {
            println(e)
            callback(false, false, GlobalValue.genericError.message)
        }
    }
}