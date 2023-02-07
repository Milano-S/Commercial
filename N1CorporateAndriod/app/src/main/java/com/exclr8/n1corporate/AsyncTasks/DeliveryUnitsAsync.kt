package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class DeliveryUnitsAsync {
    fun get(context: Context, callback: (deliveryUnits: MutableList<DeliveryUnitModel>) -> Unit) {
        val db = RoomDB.getInstance(context)

        GlobalScope.async {
            val deliveryUnits = db?.deliveryUnitsDao()?.getDeliveryUnits()!!
            callback(deliveryUnits)
        }
    }

    fun search(searchTerm: String, context: Context, callback: (deliveryUnits: MutableList<DeliveryUnitModel>) -> Unit) {
        val db = RoomDB.getInstance(context)

        GlobalScope.async {
            val deliveryUnits = db?.deliveryUnitsDao()?.getSearchDeliveryUnits(searchTerm)!!
            callback(deliveryUnits)
        }
    }

}