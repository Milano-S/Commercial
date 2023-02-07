package com.exclr8.n1corporate.DataModels.ProductModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProdCatTable")
class ProductCategoryModel {
    @JvmField
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id = 0

    @SerializedName("ProductCategoryId")
    @ColumnInfo(name = "ProductCategoryId")
    var productCategoryId = 0

    @SerializedName("ProductCategoryName")
    @ColumnInfo(name = "ProductCategoryName")
    var productCategoryName: String? = null

    @SerializedName("ItemCount")
    @ColumnInfo(name = "ItemCount")
    var itemCount = 0

    @SerializedName("ImageKey")
    @ColumnInfo(name = "ImageKey")
    var imageKey: String? = null

    constructor(productCategoryId: Int, productCategoryName: String?, itemCount: Int, imageKey: String?) {
        this.productCategoryId = productCategoryId
        this.productCategoryName = productCategoryName
        this.itemCount = itemCount
        this.imageKey = imageKey
    }
}