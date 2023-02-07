package com.exclr8.n1corporate.DataModels.ProductModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class OrderProductSpecificationModel(
val ProductCategoryId: Int?,
val SpecDescription: String?,
val SpecConfiguration: String?,
val SalesItemRevisionId: Int?,
val Fresh: Boolean?,
val Frozen: Boolean?,
val IsHalaal: Boolean?,
val Imported: Boolean?,
val FullSalesPrice: Double?,
val ImageKey: String?,
val OrderUnitType: String?,
val AddedInformation: String?,
val HasSponsor: Boolean?,
val SponsorImageKey: String?,
val Quantity: Int?,
val OrderFrequencyCount: Int?,
val IsLastOrdered: Boolean?,
val LastOrderQuantity: Int?,
val LastOrderedDate: String?,
val IsNew: Boolean?,
val DiscountedPrice: Double?,
val PackageSpecification: String?,
val CutSpecification: String?,
val IsActive: Boolean?
): Parcelable