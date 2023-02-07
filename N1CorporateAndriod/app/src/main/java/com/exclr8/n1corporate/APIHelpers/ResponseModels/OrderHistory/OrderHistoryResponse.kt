package com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderHistory

import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class OrderHistoryResponse : BaseResponse() {
    @SerializedName("DocumentDownloadUrl")
    var DocumentDownloadUrl: String? = null

    @SerializedName("Orders")
    lateinit var Orders: MutableList<OrderHistoryModel>
}