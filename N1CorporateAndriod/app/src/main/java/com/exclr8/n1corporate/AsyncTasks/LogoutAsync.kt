package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LogoutAsync(private val context: Context){

    fun execute(callback: (Success: Boolean) -> Unit){
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            if (db != null) {
                db.productCategoryDao().nuketTable()
                db.pricelistDao().nuketTable()
                db.userDao().nuketTable()
                db.deliveryUnitsDao().nuketTable()
                db.clearAllTables()
                callback(true)
            }
        }
    }
}