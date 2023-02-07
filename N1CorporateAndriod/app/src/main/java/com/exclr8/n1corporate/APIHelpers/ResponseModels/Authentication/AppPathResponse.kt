package com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response
import org.json.JSONObject

class AppPathResponse : BaseResponse() {
    @SerializedName("ApplicationPath")
    var ApplicationPath: String? = null

    @SerializedName("ApiPath")
    var ApiPath: String? = null
}

fun Response.toAppPathResponse(): AppPathResponse{
    val json = JSONObject(this.body()!!.string())
    return Gson().fromJson(json.toString(), AppPathResponse::class.java)
}