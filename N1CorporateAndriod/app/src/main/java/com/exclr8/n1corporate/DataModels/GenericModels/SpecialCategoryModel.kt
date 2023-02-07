package com.exclr8.n1corporate.DataModels.GenericModels

import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel

data class SpecialCategoryModel(
    var id: Int,
    var image: String,
    var title: String,
    var data: MutableList<ProductSpecificationModel?>?
)