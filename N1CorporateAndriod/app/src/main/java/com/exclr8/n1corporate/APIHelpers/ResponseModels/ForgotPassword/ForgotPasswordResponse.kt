package com.exclr8.n1corporate.APIHelpers.ResponseModels.ForgotPassword

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class ForgotPasswordResponse : BaseResponse() {
    @SerializedName("TempPassword")
    var TempPassword: String? = null
}