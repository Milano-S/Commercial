package com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.Response
import org.json.JSONObject

class AppConfigResponse : BaseResponse() {
    @SerializedName("DeliveryFeeThreshold")
    var DeliveryFeeThreshold: Double? = null

    @SerializedName("DeliveryFee")
    var DeliveryFee: Double? = null

    @SerializedName("PaySpaceURL")
    var PaySpaceURL: String? = null

    @SerializedName("PaySpaceReturnURL")
    var PaySpaceReturnURL: String? = null

    @SerializedName("TermsURL")
    var TermsURL: String? = null

    @SerializedName("PrivacyURL")
    var PrivacyURL: String? = null

    @SerializedName("ReturnsPolicyURL")
    var ReturnsPolicyURL: String? = null

    @SerializedName("MapApiKey")
    var MapApiKey: String? = null

    @SerializedName("DeliveryOptionsURL")
    var DeliveryOptionsURL: String? = null
}

fun Response.toAppConfigResponse(): AppConfigResponse{
    val json = JSONObject(this.body()!!.string())
    return Gson().fromJson(json.toString(), AppConfigResponse::class.java)
}