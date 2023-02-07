package com.exclr8.n1corporate.APIHelpers.ResponseModels.Profile

import com.exclr8.n1corporate.APIHelpers.ResponseModels.BaseResponse
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.google.gson.annotations.SerializedName

class ProfileDetailsResponse : BaseResponse() {
    @SerializedName("UserId")
    var UserId = 0

    @SerializedName("CollectionUnitId")
    var CollectionUnitId = 0

    @SerializedName("FirstName")
    var FirstName: String? = null

    @SerializedName("LastName")
    var LastName: String? = null

    @SerializedName("PhoneNumber")
    var PhoneNumber: String? = null

    @SerializedName("Email")
    var Email: String? = null

    var DeliveryUnits: MutableList<DeliveryUnitModel>? = null
}