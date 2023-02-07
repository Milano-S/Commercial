package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import kotlinx.coroutines.*

class ProductsAsync(val context: Context, val pid: Int){

    fun get(callback: (MutableList<ProductSpecificationModel?>) -> Unit) {
        val db = RoomDB.getInstance(context)
        GlobalScope.async {
            val productList = db?.pricelistDao()?.getPriceList(pid)!!
            callback(productList)
        }
    }
}