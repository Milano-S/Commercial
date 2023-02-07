package com.exclr8.n1corporate.APIHelpers.Login

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels.LoginDetails
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Login.LoginResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class LoginAPI(details: LoginDetails){
    lateinit var json: JSONObject
    private val client = OkHttpClient()
    val JSON = MediaType.parse("application/json")
    val jsonobj = Gson().toJson(details)
    val body = RequestBody.create(JSON, jsonobj)

    fun post(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit){

        val request = Request.Builder()
                .url(URLs().loginURL)
                .header("Content-Type", "application/json; charset=utf-8")
                .header(PreferenceHelper().getAPPKEYNAME()!!, PreferenceHelper().getAPPKEY()!!)
                .post(body)
                .build()

        try {
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        json = JSONObject(response.body()!!.string())
                        val resp = Gson().fromJson(json.toString(), LoginResponse::class.java)
                        if (!resp.Offline) {
                            if (resp.TokenKey != null) {
                                PreferenceHelper().setUSERKEYNAME(resp.TokenKeyName)
                                PreferenceHelper().setUSERKEY(resp.TokenKey)
                                callback(true, false, null)
                            } else {
                                callback(false, false, if (resp.Exception?.Message != null) resp.Exception!!.Message!!  else GlobalValue.genericError.message)
                            }
                        } else {
                            callback(true, true, null)
                        }
                    } else {
                        callback(false, false, GlobalValue.genericError.message)
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            callback(false, false, GlobalValue.genericError.message)
        }
    }
}