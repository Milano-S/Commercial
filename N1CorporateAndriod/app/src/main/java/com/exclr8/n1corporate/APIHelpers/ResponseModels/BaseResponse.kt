package com.exclr8.n1corporate.APIHelpers.ResponseModels

import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("Success")
    var Success = false

    @SerializedName("Offline")
    var Offline = false

    @SerializedName("Status")
    var Status = 0

    @SerializedName("Exception")
    var Exception: Exception? = null
}