package com.exclr8.n1corporate.DataModels.ProductModels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderHistoryModel (
    var OrderRef: String?,
    var OrderId: Int,
    var CustomerId: Int,
    var CustomerName: String,
    var DeliveryUnitId: Int,
    var DeliveryUnitName: String,
    var DeliveryUnitAddress: String,
    var IsHalaal: Boolean,
    var OrderStatusId: Int,
    var OrderStatusName: String,
    var OrderCreatorName: String,
    var LastOrderDate: String,
    var DiscountValue: Double,
    var ExVatValue: Double,
    var VatValue: Double,
    var TotalRandValue: Double,
    var DocumentType: String?,
    var DocumentDownloadURL: String?,
    var Order: MutableList<OrderProductSpecificationModel>
): Parcelable
