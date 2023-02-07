package com.exclr8.n1corporate.Fragments.OrderHistory

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Adapters.OrderHistoryDetailsAdapter
import com.exclr8.n1corporate.DataModels.Cart.OrderItemModel
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.FragmentOrderHistoryDetailsBinding


class OrderHistoryDetailsFragment: Fragment(R.layout.fragment_order_history_details) {
    lateinit var binding: FragmentOrderHistoryDetailsBinding
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOrderHistoryDetailsBinding.inflate(inflater)
        rootView = binding.root

        var orderHistoryModel = requireArguments().getParcelable<OrderHistoryModel>("OrderProductSpecificationModel")
        populateData(orderHistoryModel!!)

        binding.btnDownloadDocument.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(orderHistoryModel.DocumentDownloadURL), "application/pdf")
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            requireContext().startActivity(intent)
        }

        binding.btnReOrder.setOnClickListener {
            for (item in orderHistoryModel.Order){
                if (item.IsActive!!){
                    PreferenceHelper().addItem(OrderItemModel(item.SalesItemRevisionId!!, item.Quantity!!, item.CutSpecification!!, item.PackageSpecification!!))
                }
            }
            PreferenceHelper().setDeliveryUnit(DeliveryUnitModel(orderHistoryModel.DeliveryUnitId,orderHistoryModel.CustomerName, orderHistoryModel.DeliveryUnitName, orderHistoryModel.DeliveryUnitAddress, orderHistoryModel.IsHalaal))
            Navigation.findNavController(rootView).navigate(R.id.action_nav_history_details_to_nav_load_order_history_cart)
        }

        return rootView
    }

    fun populateData(data: OrderHistoryModel){
        binding.txtvOrderHistoryDetailsCustomerName.text = data.CustomerName
        binding.txtvOrderHistoryDetailsUnitName.text = data.DeliveryUnitName
        binding.txtvOrderHistoryDetailsUnitAddress.text = data.DeliveryUnitAddress
        binding.txtvOrderHistoryDetailsStatus.text = data.OrderStatusName
        binding.txtvOrderHistoryDetailsCreatedBy.text = data.OrderCreatorName
        binding.txtvOrderHistoryDetailsCreatedDate.text = data.LastOrderDate

        binding.rcvOrderHistoryOrderList.layoutManager = LinearLayoutManager(requireContext())
        binding.rcvOrderHistoryOrderList.adapter = OrderHistoryDetailsAdapter(data.Order, requireContext())

        binding.txtvOrderHistoryDetailsDiscountValue.text = Helper().priceFormatter(data.DiscountValue)
        binding.txtvOrderHistoryDetailsExVatValue.text = Helper().priceFormatter(data.ExVatValue)
        binding.txtvOrderHistoryDetailsVatValue.text = Helper().priceFormatter(data.VatValue)
        binding.txtvOrderHistoryDetailsTotalValue.text = Helper().priceFormatter(data.TotalRandValue)
    }
}