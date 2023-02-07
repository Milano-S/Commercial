package com.exclr8.n1corporate.DataModels.ProductModels

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "PriceListTable")
@Fts4
data class ProductSpecificationModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    var rowid:Int?,

    @SerializedName("ProductCategoryId")
    @ColumnInfo(name = "ProductCategoryId")
    var ProductCategoryId: Int?,

    @SerializedName("SpecDescription")
    @ColumnInfo(name = "SpecDescription")
    var SpecDescription: String?,

    @SerializedName("SpecConfiguration")
    @ColumnInfo(name = "SpecConfiguration")
    var SpecConfiguration: String?,

    @SerializedName("SalesItemRevisionId")
    @ColumnInfo(name = "SalesItemRevisionId")
    var SalesItemRevisionId: Int?,

    @SerializedName("Fresh")
    @ColumnInfo(name = "Fresh")
    var Fresh: Boolean?,

    @SerializedName("Frozen")
    @ColumnInfo(name = "Frozen")
    var Frozen: Boolean?,

    @SerializedName("IsHalaal")
    @ColumnInfo(name = "IsHalaal")
    var IsHalaal: Boolean?,

    @SerializedName("IsImported")
    @ColumnInfo(name = "IsImported")
    var IsImported: Boolean?,

    @SerializedName("FullSalesPrice")
    @ColumnInfo(name = "FullSalesPrice")
    var FullSalesPrice: Double?,

    @SerializedName("ImageKey")
    @ColumnInfo(name = "ImageKey")
    var ImageKey: String?,

    @SerializedName("OrderUnitType")
    @ColumnInfo(name = "OrderUnitType")
    var OrderUnitType: String?,

    @SerializedName("AddedInformation")
    @ColumnInfo(name = "AddedInformation")
    var AddedInformation: String?,

    @SerializedName("HasSponsor")
    @ColumnInfo(name = "HasSponsor")
    var HasSponsor: Boolean?,

    @SerializedName("SponsorImageKey")
    @ColumnInfo(name = "SponsorImageKey")
    var SponsorImageKey: String?,

    @SerializedName("OrderFrequencyCount")
    @ColumnInfo(name = "OrderFrequencyCount")
    var OrderFrequencyCount: Int?,

    @SerializedName("IsLastOrdered")
    @ColumnInfo(name = "IsLastOrdered")
    var IsLastOrdered: Boolean?,

    @SerializedName("LastOrderQuantity")
    @ColumnInfo(name = "LastOrderQuantity")
    var LastOrderQuantity: Int?,

    @SerializedName("LastOrderDate")
    @ColumnInfo(name = "LastOrderDate")
    var LastOrderDate: String?,

    @SerializedName("IsNew")
    @ColumnInfo(name = "IsNew")
    var IsNew: Boolean?,

    @SerializedName("DiscountedPrice")
    @ColumnInfo(name = "DiscountedPrice")
    var DiscountedPrice: Double?,

    @SerializedName("PackSpecification")
    @ColumnInfo(name = "PackSpecification")
    var PackSpecification: String?,

    @SerializedName("CutSpecification")
    @ColumnInfo(name = "CutSpecification")
    var CutSpecification: String?,

    @SerializedName("CartQuantity")
    @ColumnInfo(name = "CartQuantity")
    var CartQuantity: Int?,

    ): Parcelable