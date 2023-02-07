package com.exclr8.n1corporate.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.exclr8.n1corporate.DataModels.GenericModels.UserModel

@Dao
interface UserDao {

    @Query("Delete from UserModelTable")
    fun nuketTable()

    @Insert
    fun insertUserModel(UserModel: UserModel?)

    @Query("select UserId, FirstName, LastName, PhoneNumber, EmailAddress from UserModelTable")
    fun getUserModel(): UserModel
}