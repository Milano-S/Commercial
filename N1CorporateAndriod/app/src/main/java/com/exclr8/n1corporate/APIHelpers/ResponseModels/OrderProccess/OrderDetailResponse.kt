package com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderProccess

import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class OrderDetailResponse : BaseResponse() {
    @SerializedName("OrderAPI")
    var Order: OrderHistoryModel? = null
}