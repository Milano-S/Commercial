package com.exclr8.n1corporate.AsyncTasks

import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ClearDatabaseAsync(val context: Context){

    fun clear() {
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            if (db != null) {
                db.productCategoryDao().nuketTable()
                db.pricelistDao().nuketTable()
                db.userDao().nuketTable()
                db.deliveryUnitsDao().nuketTable()
            }
        }
    }

    fun clearProducts(){
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            if (db != null) {
                db.productCategoryDao().nuketTable()
                db.pricelistDao().nuketTable()
            }
        }
    }
}