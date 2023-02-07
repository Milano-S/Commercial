package com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderProccess

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class OrderResponse : BaseResponse() {
    @SerializedName("SalesOrderGuid")
    var SalesOrderGuid: String? = null
}