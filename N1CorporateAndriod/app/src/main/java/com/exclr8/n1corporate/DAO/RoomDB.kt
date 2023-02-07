package com.exclr8.n1corporate.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.DataModels.GenericModels.UserModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel

@Database(entities = [ProductCategoryModel::class, ProductSpecificationModel::class, UserModel::class, DeliveryUnitModel::class], version = 1)
abstract class RoomDB() : RoomDatabase() {
    companion object {
        var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB? {
            if (INSTANCE == null){
                synchronized(RoomDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, RoomDB::class.java, "N1OMS")
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }



    abstract fun productCategoryDao(): ProductCategoryDoa
    abstract fun pricelistDao(): PriceListDao
    abstract fun userDao(): UserDao
    abstract fun deliveryUnitsDao(): DeliveryUnitsDao
}