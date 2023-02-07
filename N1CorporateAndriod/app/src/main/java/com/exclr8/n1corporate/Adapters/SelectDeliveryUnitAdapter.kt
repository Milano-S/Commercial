package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.DataModels.GenericModels.DeliveryUnitModel
import com.exclr8.n1corporate.Helpers.PreferenceHelper
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardSelectDeliveryUnitBinding

class SelectDeliveryUnitAdapter(val list: MutableList<DeliveryUnitModel>, val context: Context, val profile: Boolean, val callback: (DeliveryUnitId: Int) -> Unit) : RecyclerView.Adapter<SelectDeliveryUnitAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardSelectDeliveryUnitBinding = CardSelectDeliveryUnitBinding.bind(itemView)

        init {
            itemView.setOnClickListener {
                PreferenceHelper().setDeliveryUnit(list[adapterPosition])
                callback(list[adapterPosition].Id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_select_delivery_unit, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtvCustomerName.text = list[position].CustomerName
        holder.binding.txtvDeliveryUnitName.text = list[position].DeliveryUnitName
        holder.binding.txtvDeliveryUnitAddress.text = list[position].DeliveryUnitAddress
        if (list[position].IsHalaal){
            holder.binding.imageHalaal.visible()
        }else{
            holder.binding.imageHalaal.gone()
        }

        if(profile){
            holder.binding.imageChevron.gone()
        }else{
            holder.binding.imageChevron.gone()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

