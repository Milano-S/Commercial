package com.exclr8.n1corporate.DataModels.GenericModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "UserModelTable")
class UserModel {
    @JvmField
    @PrimaryKey()
    @ColumnInfo(name = "UserId")
    var UserId: Int = 0

    @ColumnInfo(name = "FirstName")
    var FirstName = ""

    @ColumnInfo(name = "LastName")
    var LastName = ""

    @ColumnInfo(name = "PhoneNumber")
    var PhoneNumber = ""

    @ColumnInfo(name = "EmailAddress")
    var EmailAddress = ""

    constructor(UserId: Int, FirstName: String, LastName: String, PhoneNumber: String, EmailAddress: String){
        this.UserId = UserId
        this.FirstName = FirstName
        this.LastName = LastName
        this.PhoneNumber = PhoneNumber
        this.EmailAddress = EmailAddress
    }
}

