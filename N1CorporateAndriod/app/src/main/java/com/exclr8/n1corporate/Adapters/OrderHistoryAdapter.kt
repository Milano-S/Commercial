package com.exclr8.n1corporate.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.DataModels.ProductModels.OrderHistoryModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardOrderHistoryBinding

class OrderHistoryAdapter(val orderHistoryList: List<OrderHistoryModel?>, val callback: (OrderHistoryModel) -> Unit) : RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardOrderHistoryBinding = CardOrderHistoryBinding.bind(itemView)

        init {

            itemView.setOnClickListener {
                callback(orderHistoryList[adapterPosition]!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_order_history, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtvOHOrderRef.text = orderHistoryList[position]!!.OrderRef.toString()
        holder.binding.txtvOHCustomerName.text = orderHistoryList[position]!!.CustomerName
        holder.binding.txtvOHDeliveryUnitName.text = orderHistoryList[position]!!.DeliveryUnitName
        holder.binding.txtvOHOrderStatus.text = orderHistoryList[position]!!.OrderStatusName

        val date = orderHistoryList[position]!!.LastOrderDate.split("T".toRegex()).toTypedArray()
        holder.binding.txtvOHLastOrderDate.text = date[0]

        holder.binding.txtvOHTotalRandValue.text = Helper().priceFormatter(orderHistoryList[position]!!.TotalRandValue)


    }

    override fun getItemCount(): Int {
        return orderHistoryList.size
    }

}