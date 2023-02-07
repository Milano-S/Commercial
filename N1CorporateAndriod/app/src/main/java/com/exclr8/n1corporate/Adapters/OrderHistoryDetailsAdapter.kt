package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.DataModels.ProductModels.OrderProductSpecificationModel
import com.exclr8.n1corporate.Helpers.ImageLoader
import com.exclr8.n1corporate.Helpers.gone
import com.exclr8.n1corporate.Helpers.visible
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardOrderHistoryDetailsBinding

class OrderHistoryDetailsAdapter (val data: MutableList<OrderProductSpecificationModel>, val context: Context) : RecyclerView.Adapter<OrderHistoryDetailsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardOrderHistoryDetailsBinding = CardOrderHistoryDetailsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_order_history_details, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoader().get(context, data[position].ImageKey!!, holder.binding.imageOrderHistoryDetailsSpec)

        if(data[position].HasSponsor!!) {
            holder.binding.imageOrderHistoryDetailsSponsor.visible()
            ImageLoader().get(context, data[position].SponsorImageKey!!, holder.binding.imageOrderHistoryDetailsSponsor)
        }else{
            holder.binding.imageOrderHistoryDetailsSponsor.gone()
        }

        holder.binding.txtvOrderHistoryDetailsDescription.text = data[position].SpecDescription
        holder.binding.txtvOrderHistoryDetailsConfig.text = data[position].SpecConfiguration
        holder.binding.txtvOrderHistoryDetailsQuantity.text = data[position].Quantity.toString()
        holder.binding.txtvOrderHistoryDetailsUOM.text = data[position].OrderUnitType
        holder.binding.txtvOrderHistoryDetailsFullSalesPrice.text = Helper().priceFormatter(data[position].FullSalesPrice!! * data[position].Quantity!!)

        if(data[position].IsActive!!){
            holder.binding.txtvOrderHistoryDetailsError.gone()
        }else{
            holder.binding.imageOrderHistoryDetailsSpec.toGrayscale()
            holder.binding.txtvOrderHistoryDetailsError.visible()
        }

        if (data[position].IsNew!!){
            holder.binding.txtvOrderHistoryDetailsDescription.setPadding(5,0,0,0)
            holder.binding.imageOrderHistoryDetailsStar.visible()
            val id = context.resources.getIdentifier("star_new", "drawable", context.packageName)
            holder.binding.imageOrderHistoryDetailsStar.setImageResource(id)
        }else if (data[position].OrderFrequencyCount!! > 0){
            holder.binding.txtvOrderHistoryDetailsDescription.setPadding(5,0,0,0)
            holder.binding.imageOrderHistoryDetailsStar.visible()
            val id = context.resources.getIdentifier("star_freq", "drawable", context.packageName)
            holder.binding.imageOrderHistoryDetailsStar.setImageResource(id)
        }else{
            holder.binding.txtvOrderHistoryDetailsDescription.setPadding(20,0,0,0)
            holder.binding.imageOrderHistoryDetailsStar.gone()
        }

    }

    fun ImageView.toGrayscale(){
        val matrix = ColorMatrix().apply {
            setSaturation(0f)
        }
        colorFilter = ColorMatrixColorFilter(matrix)
    }

    fun ImageView.removeGrayscale(){
        val matrix = ColorMatrix().apply {
            setSaturation(1f)
        }
        colorFilter = ColorMatrixColorFilter(matrix)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}