package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchAsync(private val context: Context, private val searchTerm: String) {
    private var searchResult_list: MutableList<ProductSpecificationModel?>? = null


    fun get(callback: (Success: Boolean, Results: MutableList<ProductSpecificationModel?>?) -> Unit){
        try {
            GlobalScope.launch {
                val db = RoomDB.getInstance(context)
                val searchTermSplit = searchTerm.split(" ".toRegex()).toTypedArray()
                var newSearchTerm = ""

                for (i in searchTermSplit.indices) {
                    newSearchTerm += "%" + searchTermSplit[i] + "%"
                }

                searchResult_list = db?.pricelistDao()?.search(newSearchTerm)
                callback(true, searchResult_list)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            callback(false, null)
        }
    }
}