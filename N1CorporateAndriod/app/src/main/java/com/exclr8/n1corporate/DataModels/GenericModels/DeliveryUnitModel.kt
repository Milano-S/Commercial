package com.exclr8.n1corporate.DataModels.GenericModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "DeliveryUnitTable")
@Fts4
data class DeliveryUnitModel(

    @ColumnInfo(name = "Id")
    var Id: Int,

    @ColumnInfo(name = "CustomerName")
    var CustomerName: String,

    @ColumnInfo(name = "DeliveryUnitName")
    var DeliveryUnitName: String,

    @ColumnInfo(name = "DeliveryUnitAddress")
    var DeliveryUnitAddress: String,

    @ColumnInfo(name = "IsHalaal")
    var IsHalaal: Boolean)