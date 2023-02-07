package com.exclr8.n1corporate.APIHelpers.OrderProccess

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DataModels.Cart.OrderModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderProccess.OrderResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class OrderAPI(val order: OrderModel) {
    lateinit var json: JSONObject
    private val client = OkHttpClient()
    val gson = Gson()
    val jsonobj = gson.toJson(order)
    val JSON = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(JSON, jsonobj)
    var tokenname = PreferenceHelper().getUSERKEYNAME()
    var tokenkey = PreferenceHelper().getUSERKEY()

    fun post(callback: (Success: Boolean, Offline: Boolean, Exception: String?, SalesOrderGuid: String?) -> Unit) {
        val request = Request.Builder()
                .url(URLs().postOrderListURL)
                .header(tokenname, tokenkey)
                .post(body)
                .build()

        try {
            client.newCall(request).enqueue(object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message,null)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        json = JSONObject(response.body()!!.string())
                        val resp = Gson().fromJson(json.toString(), OrderResponse::class.java)

                        if (!resp.Offline) {
                            if (resp.Status == 1) {
                                callback(true, false, null,resp.SalesOrderGuid!!)
                            } else {
                                callback(true, false, if (resp.Exception?.Message != null) resp.Exception!!.Message!!  else GlobalValue.genericError.message,null)
                            }
                        }else{
                            callback(true, true, null,null)
                        }
                    }else{
                        callback(false, false, GlobalValue.genericError.message, null)
                    }
                }
            })
        } catch (e: Exception) {
            callback(false, false, GlobalValue.genericError.message,null)
        }
    }
}