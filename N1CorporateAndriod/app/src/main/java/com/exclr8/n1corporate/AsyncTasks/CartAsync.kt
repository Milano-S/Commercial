package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class CartAsync(val context: Context){
    lateinit var cartItemSpecs: MutableList<ProductSpecificationModel?>
    lateinit var cartItems: MutableList<OrderItemModel>
    var totalQTY = 0
    var total = 0.0

    fun get(callback: (Success: Boolean, CartItems: MutableList<OrderItemModel>, CartItemSpecs: MutableList<ProductSpecificationModel?>, total: Double, totalQTY: Int) -> Unit) {
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            cartItems = PreferenceHelper().getOrderList()
            cartItemSpecs = ArrayList()

            try {
                totalQTY = 0
                total = 0.0

                for (i in cartItems.indices) {
                    cartItemSpecs.add(db?.pricelistDao()?.getPriceListSpec(cartItems[i].salesItemRevisionId))
                    total += cartItems[i].quantity * cartItemSpecs[i]!!.FullSalesPrice!!
                    totalQTY++
                }
                callback(true, cartItems, cartItemSpecs, total, totalQTY)
            } catch (e: Exception) {
                callback(false, cartItems, cartItemSpecs, total, totalQTY)
            }
        }
    }
    }