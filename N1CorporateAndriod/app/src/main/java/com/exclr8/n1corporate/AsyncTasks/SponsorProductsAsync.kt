package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SponsorProductsAsync(val context: Context, val sponsorKey: String) {

    fun get(callback: (Success: Boolean, Sroducts: MutableList<ProductSpecificationModel?>) -> Unit) {
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            val products = db?.pricelistDao()?.getPriceListForSponsorKey(sponsorKey)
            callback(true, products!!)
        }
    }
}