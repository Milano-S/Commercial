package com.exclr8.n1corporate.APIHelpers.ResponseModels.Login

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class LoginResponse : BaseResponse() {
    @SerializedName("ExpiryUtc")
    var ExpiryUtc: String? = null

    @SerializedName("TokenKey")
    var TokenKey: String? = null

    @SerializedName("TokenKeyName")
    var TokenKeyName: String? = null
}