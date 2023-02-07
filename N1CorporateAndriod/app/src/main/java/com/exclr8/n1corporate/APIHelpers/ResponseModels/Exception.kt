package com.exclr8.n1corporate.APIHelpers.ResponseModels

import com.exclr8.n1corporate.APIHelpers.ResponseModels.Authentication.InnerException
import com.google.gson.annotations.SerializedName

class Exception {
    @SerializedName("Message")
    var Message: String? = null

    @SerializedName("InnerException")
    var InnerException: InnerException? = null
}