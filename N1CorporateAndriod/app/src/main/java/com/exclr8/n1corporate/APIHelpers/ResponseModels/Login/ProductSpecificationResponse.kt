package com.exclr8.n1corporate.APIHelpers.ResponseModels.Login

import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.google.gson.annotations.SerializedName

class ProductSpecificationResponse : BaseResponse() {
    @SerializedName("productSpecifications")
    lateinit var productSpecifications: Array<ProductSpecificationModel>
}