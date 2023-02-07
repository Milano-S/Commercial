package com.exclr8.n1corporate.Fragments.Order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentSelectDeliveryBinding

class SelectDeliveryTypeFragment : Fragment(R.layout.fragment_select_delivery) {
    lateinit var binding: FragmentSelectDeliveryBinding

    val orderItems: MutableList<OrderItemModel> = PreferenceHelper().getOrderList()
    val deliveryUnit = PreferenceHelper().getDeliveryUnit()
    var unitId: Int = 0
    var forCollection: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSelectDeliveryBinding.inflate(inflater)
        val rootView = binding.root

        totalPrice = requireArguments().getDouble("Total")

        binding.txtvSelectDeliveryCollectionBranchName.text = "Cape Town"
        binding.txtvSelectDeliveryCollectionAddress.text = "6 Pepper PLace,\nMontague Gardens,\nCape Town, 7441,\nSouth Africa"

        binding.txtvSelectDeliveryDeliveryCustomerName.text = deliveryUnit.CustomerName
        binding.txtvSelectDeliveryDeliveryUnitName.text = deliveryUnit.DeliveryUnitName
        binding.txtvSelectDeliveryDeliveryAddress.text = deliveryUnit.DeliveryUnitAddress

        binding.txtvSelectDeliveryTotalPrice.text = Helper().priceFormatter(totalPrice!!)

        if(totalPrice!! < 500){
            binding.txtvSelectDeliveryOrderError.visible()
        }else{
            binding.txtvSelectDeliveryOrderError.gone()
        }

        binding.cardCollection.setOnClickListener { v ->
            unitId = 0
            forCollection = true
            toggleDelivery()
        }

        binding.cardDelivery.setOnClickListener { v ->
            unitId = deliveryUnit.Id
            forCollection = false
            toggleDelivery()
        }

        binding.btnPlaceFinalOrder.setOnClickListener {
//            OrderAPI(OrderModel(unitId, forCollection,"","", orderItems)).post(){ Success, Offline, Exception, SalesOrderGuid ->  }
        }

        return rootView
    }

    fun toggleDelivery(){

        if (forCollection) {
            binding.btnPlaceFinalOrder.setBackgroundColor(resources.getColor(R.color.n1color))
            binding.rdBtnCollection.isChecked = true
            binding.rdBtnDelivery.isChecked = false
            binding.txtvSelectDeliveryOrderError.gone()
        }else{
            if(totalPrice!! < 500){
                binding.txtvSelectDeliveryOrderError.visible()
            }else{
                binding.txtvSelectDeliveryOrderError.gone()
            }
            binding.btnPlaceFinalOrder.setBackgroundColor(resources.getColor(R.color.gray))
            binding.rdBtnCollection.isChecked = false
            binding.rdBtnDelivery.isChecked = true
        }
    }

    companion object {
        private var totalPrice: Double? = null
    }
}
