package com.exclr8.n1corporate.APIHelpers.ResponseModels.Login

import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import kotlinx.serialization.Serializable

class ProductCategoriesResponse : BaseResponse() {
    lateinit var productCategories: MutableList<ProductCategoryModel>
}