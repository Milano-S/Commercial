package com.exclr8.n1corporate.APIHelpers

import com.exclr8.n1corporate.BuildConfig
import com.exclr8.n1corporate.Helpers.PreferenceHelper

class URLs {
    val appkey = "228069D9-CC0F-4665-A706-989ED65B8F75"

    val BASE_URL = PreferenceHelper().getBaseURLString()
    val API_BASE_URL = PreferenceHelper().getAPIBaseURLString()

    val appPathURL = BuildConfig.BASE_URL + "api/shop/n1/AppPath"
    val baseAppUserURL = API_BASE_URL + "AppUser/"

    //LOGIN
    val authURL = BASE_URL + "api/core/Authentication/Authorize"
    val loginURL = baseAppUserURL + "Login"

    //Default Data
    val getProductCategoriesURL = baseAppUserURL + "GetProductCategories"
    val getProductSpecifications = baseAppUserURL + "GetProductSpecifications"
    val getOrderHistoryURL = baseAppUserURL + "GetOrderHistory"
    val getImage = BASE_URL + "Shop/Public/Image/"
    val getAppConfigurationURL = baseAppUserURL + "AppConfiguration"
    val getUserConfigurationURL = baseAppUserURL + "UserConfiguration"
    val getSendPaymentEmail = baseAppUserURL + "SendPaymentEmail"
    val getInvoiceURL = baseAppUserURL + "GetInvoice"
    val getProfileDetailsURL = baseAppUserURL + "GetProfileDetails"


    //POST ORDER
    val postOrderListURL = baseAppUserURL + "PostOrderList"


    fun createSpecImageURL(imageKey: String): String{
        return getImage + "?type=1&imageKey=" + imageKey + "&width=" + GlobalValue.specImageSize.message
    }
//
//    fun createSponsorImageURL(imageKey: String): String{
//        return getImage + "?type=1&imageKey=" + imageKey + "&width=" + GlobalValue.specImageSponsorSize.message
//    }
//
//    fun createCardSpecImageURLType3(imageKey: String): String{
//        return getImage + "?type=3&imageKey=" + imageKey + "&width=" + GlobalValue.cardImageSize.message
//    }
//
//    fun createCardSponsorImageURLType3(imageKey: String): String{
//        return getImage + "?type=3&imageKey=" + imageKey + "&width=" + GlobalValue.cardImageSponsorSize.message
//    }
//
//    fun createSpecImageURLType3(imageKey: String): String{
//        return getImage + "?type=3&imageKey=" + imageKey + "&width=" + GlobalValue.specImageSize.message
//    }
//
//    fun createSponsorImageURLType3(imageKey: String): String{
//        return getImage + "?type=3&imageKey=" + imageKey + "&width=" + GlobalValue.specImageSponsorSize.message
//    }


}