package com.exclr8.n1corporate.APIHelpers.OrderProccess

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Login.LoginResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SendPaymentEmailAPI(val saleOrderId: String?){
    private val client = OkHttpClient()
    var offline = false
    var json: JSONObject? = null
    val JSON = MediaType.parse("application/json; charset=utf-8")
    var db: RoomDB? = null
    var saleOrderGUID = saleOrderId

    fun post(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit){
        val request = Request.Builder()
                .url(URLs().getSendPaymentEmail + "?id=" + saleOrderGUID)
                .header("Content-Type", "application/json; charset=utf-8")
                .header(PreferenceHelper().getAPPKEYNAME(), PreferenceHelper().getAPPKEY())
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
                            callback(true, false,null)
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