package com.exclr8.n1corporate.APIHelpers.RequestModels.LoginModels

import android.os.Build
import com.exclr8.n1corporate.BuildConfig
import com.google.gson.Gson
import kotlinx.serialization.*
import okhttp3.MediaType
import okhttp3.RequestBody

@Serializable
data class AppConfigModel(val OSName: String = "Android",
                          val OSVersion: String = Build.VERSION.INCREMENTAL,
                          val AppVersion: String = BuildConfig.VERSION_CODE.toString(),
                          val AppBuild: String = BuildConfig.VERSION_NAME,
                          val DeviceBrand: String = Build.BRAND,
                          val DeviceModel: String =  Build.MODEL) {
}

fun AppConfigModel.toRequestBody(): RequestBody{
    var jsonString = Gson().toJson(this)
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString)
}