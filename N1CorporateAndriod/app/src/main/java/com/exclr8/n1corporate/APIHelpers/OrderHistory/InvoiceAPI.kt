package com.exclr8.n1corporate.APIHelpers.OrderHistory

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderHistory.OrderHistoryResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class InvoiceAPI(val documentKey: String){
    lateinit var json: JSONObject
    private val client = OkHttpClient()


    fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String?, DocumentDownloadUrl: String?) -> Unit) {
        val request = Request.Builder()
                .url(URLs().getInvoiceURL + "?documentKey=" + documentKey)
                .header(PreferenceHelper().getUSERKEYNAME(), PreferenceHelper().getUSERKEY())
                .build()

        try {
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message, null)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        json = JSONObject(response.body()!!.string())
                        val resp = Gson().fromJson(json.toString(), OrderHistoryResponse::class.java)
                        if (!resp.Offline) {
                            callback(true, false, null, resp.DocumentDownloadUrl)
                        }
                    } else {
                        callback(true, true, GlobalValue.genericError.message, null)
                    }
                }
            })
        } catch (e: Exception) {
            callback(false, false, GlobalValue.genericError.message, null)
        }
    }
}