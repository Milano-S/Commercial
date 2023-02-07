package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class SpecialCategoryProductsAsync {

    fun get(context: Context, categoryId: Int, callback: (MutableList<ProductSpecificationModel?>) -> Unit){

        val db = RoomDB.getInstance(context)

        GlobalScope.async {
            when (categoryId){
                1 -> {
                    val products = db?.pricelistDao()?.getItemsOnPromotion()
                    callback(products!!)
                }
                2 ->{
                    val products = db?.pricelistDao()?.getNewItems()
                    callback(products!!)
                }
                3 ->{
                    val products = db?.pricelistDao()?.getFrequentlyOrderedItems()
                    callback(products!!)
                }
                4 ->{
                    val products = db?.pricelistDao()?.getLastOrderItems()
                    callback(products!!)
                }
            }
        }
    }
}