package com.exclr8.n1corporate.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel

@Dao
interface ProductCategoryDoa {
    @Query("Delete from ProdCatTable")
    fun nuketTable()

    @Insert
    fun insertProdCat(prodcat: ProductCategoryModel?)

    @Query("select * from ProdCatTable")
    fun getCategories(): MutableList<ProductCategoryModel?>?
}