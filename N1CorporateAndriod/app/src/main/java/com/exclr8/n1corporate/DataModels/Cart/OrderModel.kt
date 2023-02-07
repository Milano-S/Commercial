package com.exclr8.n1corporate.DataModels.Cart

import kotlinx.serialization.Serializable

@Serializable
class OrderModel(var unitId: Int,
                 var isForCollection: Boolean,
                 var orderInformation: String,
                 var deliveryInformation: String,
                 var orderItems: MutableList<OrderItemModel>)