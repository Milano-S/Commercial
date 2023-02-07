package com.exclr8.n1corporate.APIHelpers.Authorization

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels.AppConfigModel
import com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels.toRequestBody
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.AppConfigResponse
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.toAppConfigResponse
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class AppConfigurationAPI(){
    private val client = OkHttpClient()
    private val pref = PreferenceHelper()


    fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String) -> Unit){
        val request = Request.Builder()
                .url(URLs().getAppConfigurationURL)
                .headers(pref.getAppKeyHeader())
                .post(AppConfigModel().toRequestBody())
                .build()

        try {
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val resp = response.toAppConfigResponse()

                        if (!resp.Offline) {
                            pref.setDeliveryFeeThreshold(resp.DeliveryFeeThreshold!!)
                            pref.setTermsURL(resp.TermsURL!!)
                            pref.setPrivacyURL(resp.PrivacyURL!!)
                            pref.setReturnURL(resp.ReturnsPolicyURL!!)
                            pref.setGooglePlacesAPIKeyValue(resp.MapApiKey)
                            callback(true, false, "")
                        } else {
                            callback(true, true, "")
                        }
                    } else {
                        callback(false, false, GlobalValue.genericError.message)
                    }
                }
            })
        } catch (ex: Exception) {
            callback(false, false, GlobalValue.genericError.message)
        }
    }
}
