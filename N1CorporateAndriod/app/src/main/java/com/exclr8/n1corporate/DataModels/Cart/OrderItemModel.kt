package com.exclr8.n1corporate.DataModels.Cart

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
class OrderItemModel : Parcelable {
    var salesItemRevisionId = 0
    var quantity = 0
    var cutSpec = ""
    var packSpec = ""

    constructor(salesItemRevisionId: Int, quantity: Int, cutSpec: String, packSpec: String) {
        this.salesItemRevisionId = salesItemRevisionId
        this.quantity = quantity
        this.cutSpec = cutSpec
        this.packSpec = packSpec
    }

    protected constructor(`in`: Parcel) {
        salesItemRevisionId = `in`.readInt()
        quantity = `in`.readInt()
        cutSpec = `in`.readString().toString()
        packSpec = `in`.readString().toString()

    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(salesItemRevisionId)
        dest.writeInt(quantity)
        dest.writeString(cutSpec)
        dest.writeString(packSpec)
    }

    companion object CREATOR : Parcelable.Creator<OrderItemModel> {
        override fun createFromParcel(parcel: Parcel): OrderItemModel {
            return OrderItemModel(parcel)
        }

        override fun newArray(size: Int): Array<OrderItemModel?> {
            return arrayOfNulls(size)
        }
    }
}