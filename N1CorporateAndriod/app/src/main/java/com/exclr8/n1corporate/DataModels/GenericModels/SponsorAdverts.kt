package com.exclr8.n1corporate.DataModels.GenericModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SponsorAdvertsTable")
data class SponsorAdverts (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "ProductCategoryId")
    var productCategoryId: Int,

    @ColumnInfo(name = "SponsorId")
    var sponsorId: String?,

    @ColumnInfo(name = "SponsorName")
    var sponsorName: String?,

    @ColumnInfo(name = "SponsorAdvertId")
    var sponsorAdvertId:Int,

    @ColumnInfo(name = "SponsorAdvertDescription")
    var sponsorAdvertDescription: String?,

    @ColumnInfo(name = "ImageKey")
    var imageKey: String?,
)