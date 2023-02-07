package com.exclr8.n1corporate.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.exclr8.n1corporate.Helpers.Helper
import com.exclr8.n1corporate.Helpers.ImageLoader
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.CardSponsoredCategoryBinding

class SponsoredCategoryAdapter(val dataSet: MutableList<String>, val context: Context, val callback: (SponsorKey: String) -> Unit) : RecyclerView.Adapter<SponsoredCategoryAdapter.ViewHolder>() {
    var screenWidth = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: CardSponsoredCategoryBinding = CardSponsoredCategoryBinding.bind(itemView)

        init {
            val displayMetrics = context.resources.displayMetrics
            screenWidth = displayMetrics.widthPixels
            itemView.setOnClickListener { v ->
                callback(dataSet[adapterPosition])
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_sponsored_category, parent, false) as ConstraintLayout

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ImageLoader().get(context, dataSet[position], holder.binding.imageSonsoredCategory)
        if (Helper().isTablet(context)) {
            holder.binding.imageSonsoredCategory.layoutParams.width = 120
            holder.binding.imageSonsoredCategory.layoutParams.height = 120
        } else {
            holder.binding.imageSonsoredCategory.layoutParams.width = (screenWidth/3) - 60
            holder.binding.imageSonsoredCategory.layoutParams.height = (screenWidth/3) - 60
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}