package com.exclr8.n1corporate.AsyncTasks

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LifecycleCoroutineScope
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductSpecificationModel
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeScreenDataCallsAsync(val context: Context, val activity: Activity) {
    fun getData(callback: (Success: Boolean, CategoryList: MutableList<ProductCategoryModel?>?, SponsorList: MutableList<String>?, specialCategories: MutableList<SpecialCategoryModel?>?) -> Unit){
        try {
            GlobalScope.launch {
                val db = RoomDB.getInstance(context)
                val categoryList = db?.productCategoryDao()?.getCategories()
                val sponsorList = db?.pricelistDao()?.getPriceListSponsors()

                val itemsOnPromo = db?.pricelistDao()?.getItemsOnPromotion()
                val newItems = db?.pricelistDao()?.getNewItems()
                val itemsFreq = db?.pricelistDao()?.getFrequentlyOrderedItems()
                val itemsLastOrder = db?.pricelistDao()?.getLastOrderItems()


                var specialCategories  = mutableListOf<SpecialCategoryModel?>()

                if (itemsOnPromo != null) {
                    if(itemsOnPromo.count() > 0) { specialCategories.add(SpecialCategoryModel(1, "star_promo", "Items on Promotion", itemsOnPromo)) }
                }

                if (newItems != null) {
                    if (newItems.count() > 0){specialCategories.add(SpecialCategoryModel(2, "star_new", "New Items", newItems))}
                }

                if (itemsFreq != null) {
                    if (itemsFreq.count() > 0){specialCategories.add(SpecialCategoryModel(3, "star_freq", "Frequently Ordered Items", itemsFreq))}
                }

                if (itemsLastOrder != null) {
                    if (itemsLastOrder.count() > 0){specialCategories.add(SpecialCategoryModel(4, "", "Last Ordered Items", itemsLastOrder))}
                }


                activity.runOnUiThread{
                    callback(true, categoryList, sponsorList, specialCategories)
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            activity.runOnUiThread{
                callback(false, null, null, null)
            }

        }
    }
}

data class SpecialCategoryModel(
    var id: Int,
    var image: String,
    var title: String,
    var data: MutableList<ProductSpecificationModel?>?
)