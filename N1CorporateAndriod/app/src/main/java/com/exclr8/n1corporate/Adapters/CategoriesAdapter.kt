package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.APIHelpers.URLs
import com.exclr8.n1corporate.APIHelpers.GlobalValue
import com.exclr8.n1corporate.DataModels.ProductModels.ProductCategoryModel
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.ImageLoader
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardCategoryBinding

class CategoriesAdapter(val dataSet: List<ProductCategoryModel?>, val context: Context, val callback: (ProductCategoryId: Int) -> Unit) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
    var screenWidth = 0


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardCategoryBinding = CardCategoryBinding.bind(itemView)

        init {
            val displayMetrics = context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels
            itemView.setOnClickListener { v ->
                callback(dataSet[adapterPosition]!!.productCategoryId)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false) as ConstraintLayout
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Build URL and download image with Picasso Library in Offline Mode to cache images to memory
        ImageLoader().get(context, dataSet[position]!!.imageKey!!, holder.binding.imageCategory)

        if (Helper().isTablet(context)) {
            val columnCount = screenWidth / 650
            if (columnCount > 1) {
                holder.binding.imageCategory.layoutParams.width = screenWidth / columnCount - 30 * columnCount
                holder.binding.imageCategory.layoutParams.height = (screenWidth / columnCount - 30 * columnCount) / 4
            } else {
                holder.binding.imageCategory.layoutParams.width = screenWidth - 20
                holder.binding.imageCategory.layoutParams.height = screenWidth / 4 - 20
            }
        } else {
            holder.binding.imageCategory.layoutParams.width = screenWidth - 10
            holder.binding.imageCategory.layoutParams.height = screenWidth / 4 - 10
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}