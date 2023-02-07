package com.exclr8.n1corporate.AsyncTasks

import android.app.Activity
import android.content.Context
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProductSpecificationAsync(val context: Context, private val id: Int, val activity: Activity){

    fun get(callback: (Success: Boolean, ItemSpec: ProductSpecificationModel) -> Unit){
        GlobalScope.launch {
            val db = RoomDB.getInstance(context)
            val product = db?.pricelistDao()?.getPriceListSpec(id)
            if (product != null) {
                activity.runOnUiThread {
                    callback(
                        true, ProductSpecificationModel(
                            null,
                            product.ProductCategoryId,
                            product.SpecDescription,
                            product.SpecConfiguration,
                            product.SalesItemRevisionId,
                            product.Fresh,
                            product.Frozen,
                            product.IsHalaal,
                            product.IsImported,
                            product.FullSalesPrice,
                            product.ImageKey,
                            product.OrderUnitType,
                            product.AddedInformation,
                            product.HasSponsor,
                            product.SponsorImageKey,
                            product.OrderFrequencyCount,
                            product.IsLastOrdered,
                            product.LastOrderQuantity,
                            product.LastOrderDate,
                            product.IsNew,
                            product.DiscountedPrice,
                            product.PackSpecification,
                            product.CutSpecification,
                            product.CartQuantity
                        )
                    )
                }
            }
        }
    }
}