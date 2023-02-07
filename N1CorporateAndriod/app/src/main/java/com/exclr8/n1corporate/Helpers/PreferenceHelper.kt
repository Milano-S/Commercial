package com.exclr8.n1corporate.Helpers

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.google.gson.Gson
import okhttp3.Headers
import java.util.*

class PreferenceHelper{
    companion object{
        lateinit var sharedPreferences: SharedPreferences
    }

    fun initialize(activity: Activity){
        sharedPreferences = activity.getSharedPreferences("sharedPref",Context.MODE_PRIVATE)
    }

    private val APPVERSION = "appVersion"
    private val BASEURL = "baseURL"
    private val APIBASEURL = "apibaseURL"
    private val APPUSERID = "appuserid"
    private val PLACESAPIKEY = "placesApiKey"
    private val USERNAME = "username"
    private val PASSWORD = "password"
    private val APPKEY = "appkey"
    private val APPKEYNAME = "appkeyname"
    private val USERKEYNAME = "userkeyname"
    private val USERKEY = "userkey"
    private val CART = "item"
    private val CUSTOMERREF = "CustomerReferenceNumber"
    private val COLLECTIONUNITID = "CollectionUnitId"
    private val DELIVERYFEETHRESHOLD = "DeliveryFeeThreshold"
    private val DELIVERYFEE = "DeliveryFee"
    private val GUID = "guid"
    private val LOGIN = "LoginAPI"
    private val CUSTOMERNAME = "customerName"
    private val KEY = "passKeyword"
    private val SALT = "Jko0(8&5n+easDwdomo"
    private val TERMSURL = "TermsURL"
    private val PRIVACYURL = "PrivacyURL"
    private val RETURNSURL = "ReturnsURL"
    private val DELIVERYAREADATES = "DeliveryOptionsURL "
    private val HOMESCROLLPOSITION = "HomeScrollPosition"
    private val PRODUCTSCROLLPOSITION = "ProductScrollPosition"
    private val DELIVERYUNIT = "DeliveryUnit"


    var homeScrollPosition: Int
    get() {return sharedPreferences.getInt(HOMESCROLLPOSITION, 0)}
    set(value) {
        sharedPreferences.edit().putInt(HOMESCROLLPOSITION, value).apply()}

    var productScrollPosition: Int
        get() {return sharedPreferences.getInt(PRODUCTSCROLLPOSITION, 0)}
        set(value) {
            sharedPreferences.edit().putInt(PRODUCTSCROLLPOSITION, value).apply()}


    fun getAppVersionValue(): String? {
        return sharedPreferences.getString(APPVERSION, "")
    }

    fun setAppVersionValue(value: String?){
        sharedPreferences.edit().putString(APPVERSION, value).apply()
    }

    fun getAPIBaseURLString(): String{
        return sharedPreferences.getString(APIBASEURL, "")!!
    }

    fun setAPIBaseURLString(url: String){
        sharedPreferences.edit().putString(APIBASEURL, url).apply()
    }

    fun getBaseURLString(): String{
        return sharedPreferences.getString(BASEURL, "")!!
    }

    fun setBaseURLString(url: String){
        sharedPreferences.edit().putString(BASEURL, url).apply()
    }

    fun getGooglePlacesAPIKeyValue(): String? {
        return sharedPreferences.getString(PLACESAPIKEY, "")
    }

    fun setGooglePlacesAPIKeyValue(value: String?){
        sharedPreferences.edit().putString(PLACESAPIKEY, value).apply()
    }

    fun getCustomerRef(): String?{
        return sharedPreferences.getString(CUSTOMERREF, "")
    }

    fun setCustomerRef(value: String?){
        sharedPreferences.edit().putString(CUSTOMERREF, value).apply()
    }

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }


    fun getAPPKEY(): String? {
        return sharedPreferences.getString(APPKEY, "")
    }

    fun setAPPKEY(value: String?) {
        sharedPreferences.edit().putString(APPKEY, value).apply()
        sharedPreferences.edit().apply()
    }


    fun getAPPKEYNAME(): String? {
        return sharedPreferences.getString(APPKEYNAME, "ApplicationKey")
    }

    fun setAPPKEYNAME(value: String?) {
        sharedPreferences.edit().putString(APPKEYNAME, value).apply()
        sharedPreferences.edit().apply()
    }

    fun getAppKeyHeader(): Headers{
        return Headers.of(sharedPreferences.getString(APPKEY, ""), sharedPreferences.getString(APPKEYNAME, "ApplicationKey"))
    }

    fun getUSERKEYNAME(): String? {
        return sharedPreferences.getString(USERKEYNAME, "UserKeyName")
    }

    fun setUSERKEYNAME(value: String?) {
        sharedPreferences.edit().putString(USERKEYNAME, value).apply()
        sharedPreferences.edit().apply()
    }


    fun getUSERKEY(): String? {
        return sharedPreferences.getString(USERKEY, "UserKey")
    }

    fun setUSERKEY(value: String?) {
        sharedPreferences.edit().putString(USERKEY, value).apply()
        sharedPreferences.edit().apply()
    }

    fun getUserKeyHeader(): Headers{
        return Headers.of(sharedPreferences.getString(USERKEYNAME, "UserKeyName"), sharedPreferences.getString(USERKEY, "UserKey"))
    }

    fun saveItem(cartItemList: List<OrderItemModel?>) {
        val gson = Gson()
        val jsonFavorites = gson.toJson(cartItemList)
        sharedPreferences.edit().putString(CART, jsonFavorites).apply()
        sharedPreferences.edit().apply()
    }

    fun clearCart() {
        sharedPreferences.edit().remove(CART).apply()
    }

    fun addItem(product: OrderItemModel) {
        var item: MutableList<OrderItemModel> = getOrderList()
        for (i in item.indices) {
            if (item[i].salesItemRevisionId == product.salesItemRevisionId) {
                item.removeAt(i)
                break
            }
        }
        item.add(product)
        saveItem(item)
    }

    fun removeCartItem(product: OrderItemModel) {
        val items: MutableList<OrderItemModel> = getOrderList()
        for (i in items.indices){
            if (items[i].salesItemRevisionId == product.salesItemRevisionId) {
                items.removeAt(i)
                break
            }
        }
        saveItem(items)
    }

    fun getOrderList(): MutableList<OrderItemModel> {
        val items: MutableList<OrderItemModel> = mutableListOf()
        if (sharedPreferences.contains(CART)) {
            val jsonFavorites = sharedPreferences.getString(CART, null)
            val gson = Gson()
            val favoriteItems = gson.fromJson(jsonFavorites, Array<OrderItemModel>::class.java)
            items.addAll(Arrays.asList(*favoriteItems))
        } else {
            return items
        }
        return items
    }

    fun setDeliveryUnit(deliveryUnit: DeliveryUnitModel){
        val gson = Gson()
        val jsonFavorites = gson.toJson(deliveryUnit)
        sharedPreferences.edit().putString(DELIVERYUNIT, jsonFavorites).apply()
    }

    fun getDeliveryUnit(): DeliveryUnitModel{
        val jsonFavorites = sharedPreferences.getString(DELIVERYUNIT, null)
        val gson = Gson()
        val deliveryUnit = gson.fromJson(jsonFavorites, DeliveryUnitModel::class.java)
        return deliveryUnit
    }

    fun getAppUserId(): Int{
        return sharedPreferences.getInt(APPUSERID, 0)
    }

    fun setAppUserId(value: Int){
        sharedPreferences.edit().putInt(APPUSERID, value).apply()
    }

    fun getCollectionUnitId(): Int{
        return sharedPreferences.getInt(COLLECTIONUNITID, 0)
    }

    fun setCollectionUnitId(id: Int){
        sharedPreferences.edit().putInt(COLLECTIONUNITID, id).apply()
    }

    fun getDeliveryFeeThreshold(): Double?{
        return java.lang.Double.longBitsToDouble(sharedPreferences.getLong(DELIVERYFEETHRESHOLD, 0))
    }

    fun setDeliveryFeeThreshold(value: Double){
        sharedPreferences.edit().putLong(DELIVERYFEETHRESHOLD, java.lang.Double.doubleToRawLongBits(value)).apply()
    }


    fun getTermsURL(): String?{
        return sharedPreferences.getString(TERMSURL, "")
    }

    fun setTermsURL(url: String){
        sharedPreferences.edit().putString(TERMSURL, url).apply()
    }

    fun getPrivacyURL(): String?{
        return sharedPreferences.getString(PRIVACYURL, "")
    }

    fun setPrivacyURL(url: String){
        sharedPreferences.edit().putString(PRIVACYURL, url).apply()
    }

    fun getReturnURL():String?{
        return sharedPreferences.getString(RETURNSURL, "")
    }

    fun setReturnURL(url: String){
        sharedPreferences.edit().putString(RETURNSURL, url).apply()
    }


    fun LoginDetaislExists(): Boolean {
        return if (sharedPreferences.contains(USERNAME)) {
            if (sharedPreferences.contains(PASSWORD)) {
                if (getUsername() !== "" && getPassword() !== "") {
                    true
                } else {
                    setUsername("")
                    setPassword("")
                    false
                }
            } else {
                false
            }
        } else {
            false
        }
    }

    fun getUsername(): String?{
        return sharedPreferences.getString(USERNAME, "")
    }

    fun setUsername(value: String?){
        sharedPreferences.edit().putString(USERNAME, value).apply()
    }



    fun getPassword(): String?{
        val encryption = Encryption.getDefault(KEY, SALT, ByteArray(16))
        return encryption.decryptOrNull(sharedPreferences.getString(PASSWORD, ""))
    }

    fun setPassword(value: String?){
        val encryption = Encryption.getDefault(KEY, SALT, ByteArray(16))
        val editor = sharedPreferences.edit()
        editor.putString(PASSWORD, encryption.encryptOrNull(value))
        editor.apply()
    }
}