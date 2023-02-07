package com.exclr8.n1corporate.APIHelpers.Login

import android.content.Context
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Login.ProductCategoriesResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class ProductCategoriesAPI(val context: Context){
    lateinit var json: JSONObject
    val client = OkHttpClient()
    lateinit var db: RoomDB

    fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit) {
        db = RoomDB.getInstance(context)!!

//        val request = Request.Builder()
//                .url(URLs().getProductCategoriesURL)
//                .header("Content-Type", "application/json; charset=utf-8")
//                .header(PreferenceHelper().getUSERKEYNAME()!!, PreferenceHelper().getUSERKEY()!!)
//                .build()
//
//        try {
//            client.newCall(request).enqueue(object: Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    callback(false, false, GlobalValue.genericError.message)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    json = JSONObject(response.body()!!.string())
//                    val resp = Gson().fromJson(json.toString(), ProductCategoriesResponse::class.java)
//                    if (response.isSuccessful) {
//                        if (!resp.Offline) {

        val categories = """
            [
  {
    "ProductCategoryId": 66,
    "ProductCategoryName": "Butcher's Block Promos",
    "ItemCount": 3,
    "ImageKey": "c07a0476-5b14-47e9-b204-cc59b6d0bcc5",
    "SortOrder": 90
  },
  {
    "ProductCategoryId": 68,
    "ProductCategoryName": "Premium Meats ",
    "ItemCount": 9,
    "ImageKey": "ce4d9382-5f70-4d08-aa96-f60e4bcd1fc7",
    "SortOrder": 91
  },
  {
    "ProductCategoryId": 67,
    "ProductCategoryName": "Monthly Promos",
    "ItemCount": 29,
    "ImageKey": "b2a36356-16a8-40a9-a6a8-dea2b63afe24",
    "SortOrder": 92
  },
  {
    "ProductCategoryId": 58,
    "ProductCategoryName": "Bakery",
    "ItemCount": 15,
    "ImageKey": "a7fa0b73-0ec3-4227-ad0a-238cbd2e25a8",
    "SortOrder": 98
  },
  {
    "ProductCategoryId": 4,
    "ProductCategoryName": "Beef",
    "ItemCount": 21,
    "ImageKey": "c5106438-98d5-411e-b277-9e6439b426eb",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 37,
    "ProductCategoryName": "Beverages",
    "ItemCount": 10,
    "ImageKey": "84499aa7-3641-4d50-8652-69c6ea2d1d08",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 63,
    "ProductCategoryName": "Burgers & Combo Deals",
    "ItemCount": 4,
    "ImageKey": "ac108f2b-880c-41fc-ae1b-a57b87ff92fa",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 12,
    "ProductCategoryName": "Cold Meats / Bacon",
    "ItemCount": 9,
    "ImageKey": "4550040d-be8a-4c8a-be15-91278cc5b0bf",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 13,
    "ProductCategoryName": "Crumbed",
    "ItemCount": 4,
    "ImageKey": "dd7f6595-3f99-443a-9b7b-d147dbebb715",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 39,
    "ProductCategoryName": "Dairies ",
    "ItemCount": 6,
    "ImageKey": "e5b32e6f-0c6c-449c-8d78-68c826bd87c2",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 18,
    "ProductCategoryName": "Frozen Fruit",
    "ItemCount": 4,
    "ImageKey": "0cd83b60-e723-4b50-8407-267689a3a797",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 40,
    "ProductCategoryName": "Frozen Herbs",
    "ItemCount": 5,
    "ImageKey": "3ac1bd7f-01e1-47dd-915c-7e41d16be0ea",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 17,
    "ProductCategoryName": "Lamb / Mutton",
    "ItemCount": 9,
    "ImageKey": "acea8629-47e8-4ea9-9fdf-71477d64ede6",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 19,
    "ProductCategoryName": "McCains Chips",
    "ItemCount": 2,
    "ImageKey": "9ab2daa7-dde3-4f9b-b897-6c82d0b478bf",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 54,
    "ProductCategoryName": "Non Perishables",
    "ItemCount": 7,
    "ImageKey": "da43c2ec-de0c-4e55-bbfa-4afffecc0d49",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 34,
    "ProductCategoryName": "Ostrich / Game",
    "ItemCount": 6,
    "ImageKey": "7d2c4a54-9801-4f1b-9be8-8d04f9842144",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 24,
    "ProductCategoryName": "Pork",
    "ItemCount": 9,
    "ImageKey": "36ffee90-8866-49eb-bc74-db3fc479cd8e",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 27,
    "ProductCategoryName": "Potato Products",
    "ItemCount": 2,
    "ImageKey": "01e76fca-ca85-424b-809c-92ca924cdd26",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 9,
    "ProductCategoryName": "Poultry",
    "ItemCount": 5,
    "ImageKey": "86fffb12-b07f-4424-a532-eb42e769494d",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 15,
    "ProductCategoryName": "Seafood",
    "ItemCount": 18,
    "ImageKey": "a81940e8-7573-4951-94a3-79a2b2f4efcc",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 59,
    "ProductCategoryName": "Snacks",
    "ItemCount": 2,
    "ImageKey": "dfdc5cd4-111e-4ca0-a399-3ef3b7a092c4",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 44,
    "ProductCategoryName": "Spices / Marinades",
    "ItemCount": 7,
    "ImageKey": "ba4cdd3f-30de-4c09-93b4-13fe0aed5750",
    "SortOrder": 99
  },
  {
    "ProductCategoryId": 42,
    "ProductCategoryName": "Vegetables - Mc Cains",
    "ItemCount": 20,
    "ImageKey": "5b10db2d-3846-49ab-b84f-d95bb8ada777",
    "SortOrder": 99
  }
]
        """
        val jsonArray = JSONArray(categories)


                            for (i in 0 until jsonArray.length()) {
                                val category = Gson().fromJson(jsonArray.get(i).toString(), ProductCategoryModel::class.java)

                                db.productCategoryDao().insertProdCat(
                                    ProductCategoryModel(category.productCategoryId,
                                        category.productCategoryName,
                                        category.itemCount,
                                        category.imageKey)
                                )
                            }
                            callback(true, false, null)
//                        } else {
//                            callback(true, true, null)
//                        }
//                    } else {
//                        callback(false, false, GlobalValue.genericError.message)
//                    }
//                }
//            })
//        } catch (e: Exception) {
//            callback(false, false, GlobalValue.genericError.message)
//        }
    }
}