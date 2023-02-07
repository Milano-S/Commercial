package com.exclr8.n1corporate.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel

@Dao
interface DeliveryUnitsDao {
    @Query("Delete from DeliveryUnitTable")
    fun nuketTable()

    @Insert
    fun insertDeliveryUnit(deliveryUnit: DeliveryUnitModel?)

    @Query("select Id, CustomerName, DeliveryUnitName, DeliveryUnitAddress, IsHalaal from DeliveryUnitTable")
    fun getDeliveryUnits(): MutableList<DeliveryUnitModel>

    @Query("select Id, CustomerName, DeliveryUnitName, DeliveryUnitAddress, IsHalaal from DeliveryUnitTable where CustomerName like :searchTerm or DeliveryUnitName like :searchTerm")
    fun getSearchDeliveryUnits(searchTerm: String): MutableList<DeliveryUnitModel>

}

