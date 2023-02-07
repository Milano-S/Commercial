package com.exclr8.n1corporate.APIHelpers.OrderHistory

import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.APIHelpers.ResponseModels.OrderHistory.OrderHistoryResponse
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.DataModels.ProductModels.OrderProductSpecificationModel
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.random.Random

class OrderHistoryAPI(){
    var json: JSONObject? = null
    val client = OkHttpClient()
    lateinit var db: RoomDB

    fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String?, OrderList: MutableList<OrderHistoryModel>?) -> Unit) {

        val ordrhistory = mutableListOf<OrderHistoryModel>()

            ordrhistory += OrderHistoryModel( "Order 01",
                1,
                1,
                "Feedem",
                1,
                "Pearson Durbanville",
                "9 Roger st, Tyger Valley, Cape Town, 7530",
                false,
                1,
                "Pending",
                "Allan",
                "2021/02/04",
                0.0,
                1104.79,
                194.96,
                1299.75,
                "",
                "https://www.clickdimensions.com/links/TestPDFfile.pdf",
                arrayOf(
                    OrderProductSpecificationModel( 67,
                "Beef Brisket",
                "1kg portion, sliced",
                70969,
                false,
                false,
                true,
                false,
                89.98,
                "87f39610-b779-4874-923b-6333ab3b616c",
                "kg",
                "Great for slow roasting, tender and delicious.",
                false,
                "",
                5,
                1,
                false,
                5,
                "2021/05/11",
                false,
                0.0,
                "In 5kg boxes",
                "200g cuts",
                true),
            OrderProductSpecificationModel( 13,
                "Chicken Supreme Crumbed Burger",
                "Boxed (2.50 kg)",
                75291,
                false,
                false,
                true,
                false,
                169.97,
                "9a9c55ee-5f3f-4aea-824f-629b839ed913",
                "x",
                "",
                false,
                "",
                5,
                1,
                false,
                5,
                "2021/05/11",
                false,
                0.0,
                "",
                "",
                false)).toMutableList()
            )

    callback(true, false, "", ordrhistory)

//        val request = Request.Builder()
//                .url(URLs().getOrderHistoryURL)
//                .header("Content-Type", "application/json; charset=utf-8")
//                .header(PreferenceHelper().getUSERKEYNAME()!!, PreferenceHelper().getUSERKEY()!!)
//                .build()
//        try {
//            client.newCall(request).enqueue(object: Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    callback(false, false, GlobalValue.genericError.message, null)
//                }
//
//                override fun onResponse(call: Call, response: Response) {
//                    if (response.isSuccessful) {
//                        json = JSONObject(response.body()!!.string())
//                        val resp = Gson().fromJson(json.toString(), OrderHistoryResponse::class.java)
//
//                        if (!resp.Offline) {
//
//                            callback(true, false, null, resp.Orders)
//                        }else{
//                            callback(true, true, null,null)
//                        }
//                    } else {
//                        callback(false, false, GlobalValue.genericError.message,null)
//                    }
//                }
//            })
//        } catch (e: Exception) {
//            callback(false, false, GlobalValue.genericError.message, null)
//        }
    }
}