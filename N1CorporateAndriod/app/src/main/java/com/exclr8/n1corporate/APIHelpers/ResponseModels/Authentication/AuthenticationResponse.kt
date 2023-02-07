package com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class AuthenticationResponse : BaseResponse() {
    @SerializedName("TokenKeyName")
    var TokenKeyName: String? = null

    @SerializedName("TokenKey")
    var TokenKey: String? = null
}