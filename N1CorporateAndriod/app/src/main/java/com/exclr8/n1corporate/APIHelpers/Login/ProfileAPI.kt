package com.exclr8.n1corporate.APIHelpers.Login

import android.content.Context
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.APIHelpers.ResponseModels.Profile.ProfileDetailsResponse
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.DAO.RoomDB
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.DataModels.GenericModels.UserModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.google.gson.Gson
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ProfileAPI(val context: Context){
    lateinit var json: JSONObject
    private val client = OkHttpClient()

    fun get(callback: (Success: Boolean, Offline: Boolean, Exception: String?) -> Unit){
        val db = RoomDB.getInstance(context)

        val request = Request.Builder()
            .url(URLs().getProfileDetailsURL)
            .header("Content-Type", "application/json; charset=utf-8")
            .header(PreferenceHelper().getUSERKEYNAME()!!, PreferenceHelper().getUSERKEY()!!)
            .build()

        try {
            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    callback(false, false, GlobalValue.genericError.message)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        json = JSONObject(response.body()!!.string())
                        val resp = Gson().fromJson(json.toString(), ProfileDetailsResponse::class.java)
                        if (!resp.Offline) {

                            db!!.userDao().insertUserModel(UserModel(1, resp.FirstName!!, resp.LastName!!, resp.PhoneNumber!!, resp.Email!!))

                            val deliveryUnits = mutableListOf<DeliveryUnitModel>()
                            deliveryUnits.add(
                                DeliveryUnitModel(1, "Feedem", "Pearson Cape Town","19 Hertzog Blvd,\nCape Town City Centre,\nCape Town,\n8000", false)
                            )

                            deliveryUnits.add(
                                DeliveryUnitModel(2, "Feedem", "Peasron Durbanville","9 Rogers St,\nTyger Valley,\nCape Town\n7530", true)
                            )
                            for (deliveryUnit in deliveryUnits){
                                db.deliveryUnitsDao().insertDeliveryUnit(DeliveryUnitModel(deliveryUnit.Id, deliveryUnit.CustomerName, deliveryUnit.DeliveryUnitName, deliveryUnit.DeliveryUnitAddress, deliveryUnit.IsHalaal))
                            }

                                callback(true, false, null)
                        } else {
                            callback(true, true, null)
                        }
                    } else {
                        callback(false, false, GlobalValue.genericError.message)
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
            callback(false, false, GlobalValue.genericError.message)
        }
    }
}